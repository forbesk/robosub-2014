<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE eagle SYSTEM "eagle.dtd">
<eagle version="6.4">
<drawing>
<settings>
<setting alwaysvectorfont="yes"/>
<setting verticaltext="up"/>
</settings>
<grid distance="0.1" unitdist="inch" unit="inch" style="lines" multiple="1" display="no" altdistance="0.01" altunitdist="inch" altunit="inch"/>
<layers>
<layer number="1" name="Top" color="4" fill="1" visible="no" active="no"/>
<layer number="16" name="Bottom" color="1" fill="1" visible="no" active="no"/>
<layer number="17" name="Pads" color="2" fill="1" visible="no" active="no"/>
<layer number="18" name="Vias" color="2" fill="1" visible="no" active="no"/>
<layer number="19" name="Unrouted" color="6" fill="1" visible="no" active="no"/>
<layer number="20" name="Dimension" color="15" fill="1" visible="no" active="no"/>
<layer number="21" name="tPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="22" name="bPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="23" name="tOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="24" name="bOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="25" name="tNames" color="7" fill="1" visible="no" active="no"/>
<layer number="26" name="bNames" color="7" fill="1" visible="no" active="no"/>
<layer number="27" name="tValues" color="7" fill="1" visible="no" active="no"/>
<layer number="28" name="bValues" color="7" fill="1" visible="no" active="no"/>
<layer number="29" name="tStop" color="7" fill="3" visible="no" active="no"/>
<layer number="30" name="bStop" color="7" fill="6" visible="no" active="no"/>
<layer number="31" name="tCream" color="7" fill="4" visible="no" active="no"/>
<layer number="32" name="bCream" color="7" fill="5" visible="no" active="no"/>
<layer number="33" name="tFinish" color="6" fill="3" visible="no" active="no"/>
<layer number="34" name="bFinish" color="6" fill="6" visible="no" active="no"/>
<layer number="35" name="tGlue" color="7" fill="4" visible="no" active="no"/>
<layer number="36" name="bGlue" color="7" fill="5" visible="no" active="no"/>
<layer number="37" name="tTest" color="7" fill="1" visible="no" active="no"/>
<layer number="38" name="bTest" color="7" fill="1" visible="no" active="no"/>
<layer number="39" name="tKeepout" color="4" fill="11" visible="no" active="no"/>
<layer number="40" name="bKeepout" color="1" fill="11" visible="no" active="no"/>
<layer number="41" name="tRestrict" color="4" fill="10" visible="no" active="no"/>
<layer number="42" name="bRestrict" color="1" fill="10" visible="no" active="no"/>
<layer number="43" name="vRestrict" color="2" fill="10" visible="no" active="no"/>
<layer number="44" name="Drills" color="7" fill="1" visible="no" active="no"/>
<layer number="45" name="Holes" color="7" fill="1" visible="no" active="no"/>
<layer number="46" name="Milling" color="3" fill="1" visible="no" active="no"/>
<layer number="47" name="Measures" color="7" fill="1" visible="no" active="no"/>
<layer number="48" name="Document" color="7" fill="1" visible="no" active="no"/>
<layer number="49" name="Reference" color="7" fill="1" visible="no" active="no"/>
<layer number="51" name="tDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="52" name="bDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="91" name="Nets" color="2" fill="1" visible="yes" active="yes"/>
<layer number="92" name="Busses" color="1" fill="1" visible="yes" active="yes"/>
<layer number="93" name="Pins" color="2" fill="1" visible="no" active="yes"/>
<layer number="94" name="Symbols" color="4" fill="1" visible="yes" active="yes"/>
<layer number="95" name="Names" color="7" fill="1" visible="yes" active="yes"/>
<layer number="96" name="Values" color="7" fill="1" visible="yes" active="yes"/>
<layer number="97" name="Info" color="7" fill="1" visible="yes" active="yes"/>
<layer number="98" name="Guide" color="6" fill="1" visible="yes" active="yes"/>
</layers>
<schematic xreflabel="%F%N/%S.%C%R" xrefpart="/%S.%C%R">
<libraries>
<library name="custom">
<packages>
<package name="UMAX10">
<smd name="1" x="-2.14" y="1" dx="1.4" dy="0.28" layer="1"/>
<smd name="2" x="-2.14" y="0.5" dx="1.4" dy="0.28" layer="1"/>
<smd name="3" x="-2.14" y="0" dx="1.4" dy="0.28" layer="1"/>
<smd name="4" x="-2.14" y="-0.5" dx="1.4" dy="0.28" layer="1"/>
<smd name="5" x="-2.14" y="-1" dx="1.4" dy="0.28" layer="1"/>
<smd name="6" x="2.14" y="-1" dx="1.4" dy="0.28" layer="1"/>
<smd name="7" x="2.14" y="-0.5" dx="1.4" dy="0.28" layer="1"/>
<smd name="8" x="2.14" y="0" dx="1.4" dy="0.28" layer="1"/>
<smd name="9" x="2.14" y="0.5" dx="1.4" dy="0.28" layer="1"/>
<smd name="10" x="2.14" y="1" dx="1.4" dy="0.28" layer="1"/>
<wire x1="-1.5" y1="1.5" x2="1.5" y2="1.5" width="0.127" layer="21"/>
<wire x1="1.5" y1="1.5" x2="1.5" y2="-1.5" width="0.127" layer="21"/>
<wire x1="1.5" y1="-1.5" x2="-1.5" y2="-1.5" width="0.127" layer="21"/>
<wire x1="-1.5" y1="-1.5" x2="-1.5" y2="1.5" width="0.127" layer="21"/>
<circle x="-2" y="1.5" radius="0.2" width="0.127" layer="21"/>
<text x="-1.5" y="1.7" size="0.5" layer="21">&gt;NAME</text>
</package>
<package name="SMB50R">
<pad name="P$1" x="-2.54" y="2.54" drill="1.7018"/>
<pad name="P$2" x="-2.54" y="-2.54" drill="1.7018"/>
<pad name="P$3" x="2.54" y="-2.54" drill="1.7018"/>
<pad name="P$4" x="2.54" y="2.54" drill="1.7018"/>
<pad name="P$5" x="0" y="0" drill="1.1684"/>
<text x="-3.81" y="5.08" size="1.27" layer="21">&gt;NAME</text>
</package>
</packages>
<symbols>
<symbol name="MAX9939">
<pin name="VCC" x="-15.24" y="10.16" visible="pin" length="middle"/>
<pin name="INA+" x="-15.24" y="5.08" visible="pin" length="middle"/>
<pin name="INA-" x="-15.24" y="2.54" visible="pin" length="middle"/>
<pin name="SCLK" x="-15.24" y="-2.54" visible="pin" length="middle"/>
<pin name="DIN" x="-15.24" y="-5.08" visible="pin" length="middle"/>
<pin name="!CS" x="-15.24" y="-7.62" visible="pin" length="middle"/>
<pin name="GND" x="-15.24" y="-12.7" visible="pin" length="middle"/>
<pin name="INB" x="15.24" y="2.54" visible="pin" length="middle" rot="R180"/>
<pin name="OUTA" x="15.24" y="-2.54" visible="pin" length="middle" rot="R180"/>
<pin name="OUTB" x="15.24" y="-5.08" visible="pin" length="middle" rot="R180"/>
<wire x1="-10.16" y1="12.7" x2="10.16" y2="12.7" width="0.254" layer="94"/>
<wire x1="10.16" y1="12.7" x2="10.16" y2="-15.24" width="0.254" layer="94"/>
<wire x1="10.16" y1="-15.24" x2="-10.16" y2="-15.24" width="0.254" layer="94"/>
<wire x1="-10.16" y1="-15.24" x2="-10.16" y2="12.7" width="0.254" layer="94"/>
<text x="-10.16" y="15.24" size="1.778" layer="95">&gt;NAME</text>
<text x="-10.16" y="-17.78" size="1.778" layer="95">&gt;VALUE</text>
</symbol>
<symbol name="SMB">
<pin name="2" x="10.16" y="0" visible="off" rot="R180"/>
<circle x="0" y="0" radius="2.54" width="0.254" layer="94"/>
<circle x="0" y="0" radius="5.08" width="0.254" layer="94"/>
<pin name="1" x="0" y="-10.16" visible="off" length="middle" rot="R90"/>
<text x="-5.08" y="7.62" size="1.27" layer="95">&gt;NAME</text>
</symbol>
</symbols>
<devicesets>
<deviceset name="MAX9939" prefix="IC">
<gates>
<gate name="G$1" symbol="MAX9939" x="0" y="0"/>
</gates>
<devices>
<device name="" package="UMAX10">
<connects>
<connect gate="G$1" pin="!CS" pad="10"/>
<connect gate="G$1" pin="DIN" pad="2"/>
<connect gate="G$1" pin="GND" pad="3"/>
<connect gate="G$1" pin="INA+" pad="5"/>
<connect gate="G$1" pin="INA-" pad="4"/>
<connect gate="G$1" pin="INB" pad="7"/>
<connect gate="G$1" pin="OUTA" pad="8"/>
<connect gate="G$1" pin="OUTB" pad="6"/>
<connect gate="G$1" pin="SCLK" pad="1"/>
<connect gate="G$1" pin="VCC" pad="9"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="SMB50R" prefix="J">
<gates>
<gate name="G$1" symbol="SMB" x="0" y="0"/>
</gates>
<devices>
<device name="" package="SMB50R">
<connects>
<connect gate="G$1" pin="1" pad="P$1 P$2 P$3 P$4"/>
<connect gate="G$1" pin="2" pad="P$5"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="supply1">
<description>&lt;b&gt;Supply Symbols&lt;/b&gt;&lt;p&gt;
 GND, VCC, 0V, +5V, -5V, etc.&lt;p&gt;
 Please keep in mind, that these devices are necessary for the
 automatic wiring of the supply signals.&lt;p&gt;
 The pin name defined in the symbol is identical to the net which is to be wired automatically.&lt;p&gt;
 In this library the device names are the same as the pin names of the symbols, therefore the correct signal names appear next to the supply symbols in the schematic.&lt;p&gt;
 &lt;author&gt;Created by librarian@cadsoft.de&lt;/author&gt;</description>
<packages>
</packages>
<symbols>
<symbol name="+3V3">
<wire x1="1.27" y1="-1.905" x2="0" y2="0" width="0.254" layer="94"/>
<wire x1="0" y1="0" x2="-1.27" y2="-1.905" width="0.254" layer="94"/>
<text x="-2.54" y="-5.08" size="1.778" layer="96" rot="R90">&gt;VALUE</text>
<pin name="+3V3" x="0" y="-2.54" visible="off" length="short" direction="sup" rot="R90"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="+3V3" prefix="+3V3">
<description>&lt;b&gt;SUPPLY SYMBOL&lt;/b&gt;</description>
<gates>
<gate name="G$1" symbol="+3V3" x="0" y="0"/>
</gates>
<devices>
<device name="">
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="supply2">
<description>&lt;b&gt;Supply Symbols&lt;/b&gt;&lt;p&gt;
GND, VCC, 0V, +5V, -5V, etc.&lt;p&gt;
Please keep in mind, that these devices are necessary for the
automatic wiring of the supply signals.&lt;p&gt;
The pin name defined in the symbol is identical to the net which is to be wired automatically.&lt;p&gt;
In this library the device names are the same as the pin names of the symbols, therefore the correct signal names appear next to the supply symbols in the schematic.&lt;p&gt;
&lt;author&gt;Created by librarian@cadsoft.de&lt;/author&gt;</description>
<packages>
</packages>
<symbols>
<symbol name="GND">
<wire x1="-1.27" y1="0" x2="1.27" y2="0" width="0.254" layer="94"/>
<wire x1="1.27" y1="0" x2="0" y2="-1.27" width="0.254" layer="94"/>
<wire x1="0" y1="-1.27" x2="-1.27" y2="0" width="0.254" layer="94"/>
<text x="-1.905" y="-3.175" size="1.778" layer="96">&gt;VALUE</text>
<pin name="GND" x="0" y="2.54" visible="off" length="short" direction="sup" rot="R270"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="GND" prefix="SUPPLY">
<description>&lt;b&gt;SUPPLY SYMBOL&lt;/b&gt;</description>
<gates>
<gate name="GND" symbol="GND" x="0" y="0"/>
</gates>
<devices>
<device name="">
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
</libraries>
<attributes>
</attributes>
<variantdefs>
</variantdefs>
<classes>
<class number="0" name="default" width="0" drill="0">
</class>
</classes>
<parts>
<part name="IC1" library="custom" deviceset="MAX9939" device=""/>
<part name="IC2" library="custom" deviceset="MAX9939" device=""/>
<part name="IC3" library="custom" deviceset="MAX9939" device=""/>
<part name="J1" library="custom" deviceset="SMB50R" device=""/>
<part name="J2" library="custom" deviceset="SMB50R" device=""/>
<part name="J3" library="custom" deviceset="SMB50R" device=""/>
<part name="+3V1" library="supply1" deviceset="+3V3" device=""/>
<part name="SUPPLY1" library="supply2" deviceset="GND" device=""/>
</parts>
<sheets>
<sheet>
<plain>
</plain>
<instances>
<instance part="IC1" gate="G$1" x="15.24" y="43.18"/>
<instance part="IC2" gate="G$1" x="15.24" y="2.54"/>
<instance part="IC3" gate="G$1" x="15.24" y="-38.1"/>
<instance part="J1" gate="G$1" x="-30.48" y="48.26"/>
<instance part="J2" gate="G$1" x="-30.48" y="7.62"/>
<instance part="J3" gate="G$1" x="-30.48" y="-33.02"/>
<instance part="+3V1" gate="G$1" x="-5.08" y="63.5"/>
<instance part="SUPPLY1" gate="GND" x="-2.54" y="-60.96"/>
</instances>
<busses>
</busses>
<nets>
<net name="N$1" class="0">
<segment>
<pinref part="J1" gate="G$1" pin="2"/>
<pinref part="IC1" gate="G$1" pin="INA+"/>
<wire x1="-20.32" y1="48.26" x2="0" y2="48.26" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$2" class="0">
<segment>
<pinref part="J1" gate="G$1" pin="1"/>
<wire x1="-30.48" y1="38.1" x2="-20.32" y2="38.1" width="0.1524" layer="91"/>
<wire x1="-20.32" y1="38.1" x2="-20.32" y2="45.72" width="0.1524" layer="91"/>
<pinref part="IC1" gate="G$1" pin="INA-"/>
<wire x1="-20.32" y1="45.72" x2="0" y2="45.72" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$3" class="0">
<segment>
<pinref part="J2" gate="G$1" pin="1"/>
<wire x1="-30.48" y1="-2.54" x2="-20.32" y2="-2.54" width="0.1524" layer="91"/>
<wire x1="-20.32" y1="-2.54" x2="-20.32" y2="5.08" width="0.1524" layer="91"/>
<pinref part="IC2" gate="G$1" pin="INA-"/>
<wire x1="-20.32" y1="5.08" x2="0" y2="5.08" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$4" class="0">
<segment>
<pinref part="J2" gate="G$1" pin="2"/>
<pinref part="IC2" gate="G$1" pin="INA+"/>
<wire x1="-20.32" y1="7.62" x2="0" y2="7.62" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$5" class="0">
<segment>
<pinref part="J3" gate="G$1" pin="2"/>
<pinref part="IC3" gate="G$1" pin="INA+"/>
<wire x1="-20.32" y1="-33.02" x2="0" y2="-33.02" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$6" class="0">
<segment>
<pinref part="J3" gate="G$1" pin="1"/>
<wire x1="-30.48" y1="-43.18" x2="-20.32" y2="-43.18" width="0.1524" layer="91"/>
<wire x1="-20.32" y1="-43.18" x2="-20.32" y2="-35.56" width="0.1524" layer="91"/>
<pinref part="IC3" gate="G$1" pin="INA-"/>
<wire x1="-20.32" y1="-35.56" x2="0" y2="-35.56" width="0.1524" layer="91"/>
</segment>
</net>
<net name="+3V3" class="0">
<segment>
<pinref part="+3V1" gate="G$1" pin="+3V3"/>
<wire x1="-5.08" y1="60.96" x2="-5.08" y2="53.34" width="0.1524" layer="91"/>
<pinref part="IC3" gate="G$1" pin="VCC"/>
<wire x1="-5.08" y1="53.34" x2="-5.08" y2="12.7" width="0.1524" layer="91"/>
<wire x1="-5.08" y1="12.7" x2="-5.08" y2="-27.94" width="0.1524" layer="91"/>
<wire x1="-5.08" y1="-27.94" x2="0" y2="-27.94" width="0.1524" layer="91"/>
<pinref part="IC2" gate="G$1" pin="VCC"/>
<wire x1="-5.08" y1="12.7" x2="0" y2="12.7" width="0.1524" layer="91"/>
<junction x="-5.08" y="12.7"/>
<pinref part="IC1" gate="G$1" pin="VCC"/>
<wire x1="-5.08" y1="53.34" x2="0" y2="53.34" width="0.1524" layer="91"/>
<junction x="-5.08" y="53.34"/>
</segment>
</net>
<net name="GND" class="0">
<segment>
<pinref part="IC1" gate="G$1" pin="GND"/>
<pinref part="SUPPLY1" gate="GND" pin="GND"/>
<wire x1="0" y1="30.48" x2="-2.54" y2="30.48" width="0.1524" layer="91"/>
<wire x1="-2.54" y1="30.48" x2="-2.54" y2="-10.16" width="0.1524" layer="91"/>
<pinref part="IC3" gate="G$1" pin="GND"/>
<wire x1="-2.54" y1="-10.16" x2="-2.54" y2="-50.8" width="0.1524" layer="91"/>
<wire x1="-2.54" y1="-50.8" x2="-2.54" y2="-58.42" width="0.1524" layer="91"/>
<wire x1="0" y1="-50.8" x2="-2.54" y2="-50.8" width="0.1524" layer="91"/>
<junction x="-2.54" y="-50.8"/>
<pinref part="IC2" gate="G$1" pin="GND"/>
<wire x1="0" y1="-10.16" x2="-2.54" y2="-10.16" width="0.1524" layer="91"/>
<junction x="-2.54" y="-10.16"/>
</segment>
</net>
<net name="N$7" class="0">
<segment>
<pinref part="IC1" gate="G$1" pin="SCLK"/>
<wire x1="0" y1="40.64" x2="-7.62" y2="40.64" width="0.1524" layer="91"/>
<wire x1="-7.62" y1="40.64" x2="-7.62" y2="0" width="0.1524" layer="91"/>
<pinref part="IC2" gate="G$1" pin="SCLK"/>
<wire x1="-7.62" y1="0" x2="0" y2="0" width="0.1524" layer="91"/>
<pinref part="IC3" gate="G$1" pin="SCLK"/>
<wire x1="0" y1="-40.64" x2="-7.62" y2="-40.64" width="0.1524" layer="91"/>
<wire x1="-7.62" y1="-40.64" x2="-7.62" y2="0" width="0.1524" layer="91"/>
<junction x="-7.62" y="0"/>
</segment>
</net>
</nets>
</sheet>
</sheets>
</schematic>
</drawing>
</eagle>
