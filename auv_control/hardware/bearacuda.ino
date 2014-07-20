/*
 * motor_control - Bridge between motor control and
 * desired motor signals.
 *
 * Author(s): Evan Briones and Kevin Forbes
 *
 * Copyright (C) 2013 AUVUA Club
 *
 */

#include <SoftwareSerial.h>

// Serial
#define TXPIN 2
#define RXPIN 4
#define WHITEPIN 13
#define REDPIN 11
#define GREENPIN 10
#define BLUEPIN 9
#define DEPTHPIN A5

#define BAUD_RATE 19200
#define TIMEOUT_THRESHOLD 1000
#define TELEMETRY_TIMEOUT 50

#define EMPTY_BYTE 0x00
#define PACKET_LENGTH 26
#define MOTOR_PACKET_LENGTH 15
#define INDICATOR_PACKET_LENGTH 8
#define STOP_SPEED 0
#define ACCELERATION_LIMIT 1
#define DECELERATION_LIMIT 2
#define LIMIT_VALUE 2

// Flags
#define READY_FLAG 0x7F
#define SAFE_START_FLAG 0x83
#define CONTROL_FLAG 0xAA
#define LIMIT_FLAG 0x22
#define FORWARD_FLAG 0x05
#define REVERSE_FLAG 0x06
#define DEPTH_SENSOR 0x64 //'d' in ascii

long current_time;
long previous_time;
SoftwareSerial serial_controller = SoftwareSerial(RXPIN, TXPIN);


void setMotorSpeed(uint8_t dir, uint8_t speed, uint8_t device_id) {
    byte packet[5] = {0};

    speed = speed < 0 ? 0 : (speed > 100 ? 100 : speed);
    speed = speed / 2;

    packet[0] = CONTROL_FLAG;
    packet[1] = device_id;
    packet[2] = (dir == 1) ? REVERSE_FLAG : FORWARD_FLAG;
    packet[3] = EMPTY_BYTE;
    packet[4] = speed;

    serial_controller.write(packet, 5);
}

void setMotorLimit(uint8_t device_id, uint8_t limit_type, int limit_value) {
    byte packet[6] = {0};

    packet[0] = CONTROL_FLAG;
    packet[1] = device_id;
    packet[2] = LIMIT_FLAG;
    packet[3] = limit_type;
    packet[4] = limit_value % 128;
    packet[5] = limit_value >> 7;

    serial_controller.write(packet, 6);
}

void setIndicator(uint8_t pin, uint8_t value) {
    analogWrite(pin, value);
}


/**
 * pressure = (sensorValue - 0.204) / 0.0204
 */
void sendSensorData() {
    static long previous_time;

    if (abs(previous_time - current_time) > TELEMETRY_TIMEOUT) {
        previous_time = current_time;
        word sensorValue1 = analogRead(DEPTHPIN);
        byte packet[3] = {0};

        packet[0] = DEPTH_SENSOR;
        packet[1] = highByte(sensorValue1);
        packet[2] = lowByte(sensorValue1);
        Serial.write(packet, 3);
    }
}
void setup() {
    Serial.begin(BAUD_RATE);
    serial_controller.begin(BAUD_RATE);

    // Setup pins
    pinMode(TXPIN, OUTPUT);
    pinMode(RXPIN, INPUT);
    pinMode(WHITEPIN, OUTPUT);
    pinMode(REDPIN, OUTPUT);
    pinMode(GREENPIN, OUTPUT);
    pinMode(BLUEPIN, OUTPUT);
    
    analogWrite(WHITEPIN, 0);
    analogWrite(REDPIN, 255);
    analogWrite(GREENPIN, 0);
    analogWrite(BLUEPIN, 255);
    
    delay(1000);
    
    analogWrite(REDPIN, 0);
    analogWrite(BLUEPIN, 0);

    // Setup timer
    current_time = previous_time = millis();

    // Start motor controllers
    serial_controller.write(SAFE_START_FLAG);
    delay(5);

    for (uint8_t i = 1; i < 6; i++) {
        setMotorLimit(i, ACCELERATION_LIMIT, LIMIT_VALUE);
        delay(1);  //wait one ms between commands
        setMotorLimit(i, DECELERATION_LIMIT, LIMIT_VALUE);
        delay(1);
    }
    
}

void loop() {

    // Controller doesn't send back info yet.
    if (serial_controller.available()) {
    }

    if(Serial.peek() != READY_FLAG) {
      Serial.read();
    } else if (Serial.available() >= PACKET_LENGTH) {
        Serial.read();
        byte packet[PACKET_LENGTH] = {0};

        for (int i = 0; i < PACKET_LENGTH; i++) {
          packet[i] = Serial.read();
        }

        //if (get_checksum(packet) > 0)
        //    return;

        for (int i = 0; i < MOTOR_PACKET_LENGTH; i += 3) {
            setMotorSpeed(packet[i+1], packet[i+2], packet[i]);
        }
        
        for (int i = MOTOR_PACKET_LENGTH; i < MOTOR_PACKET_LENGTH + INDICATOR_PACKET_LENGTH; i += 2) {
            setIndicator(packet[i], packet[i+1]); 
        }

        //temporary fix for semi-broken motor controller
        serial_controller.write(SAFE_START_FLAG);

        previous_time = millis();
    }

    if ((current_time - previous_time) > TIMEOUT_THRESHOLD) {
        for (int i = 0; i < 5; i++)
           setMotorSpeed(0, STOP_SPEED, byte(i));
    }

    current_time = millis();
    //sendSensorData();
}

int get_checksum(byte packet[]) {
    word checksum = 0;

    for (int i = 0; i < PACKET_LENGTH - 2; i += 2) {
        checksum += word(packet[i], packet[i+1]);
    }

    checksum -= word(packet[PACKET_LENGTH-2], packet[PACKET_LENGTH-1]);
    return checksum;
}
