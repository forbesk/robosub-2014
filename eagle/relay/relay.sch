<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE eagle SYSTEM "eagle.dtd">
<eagle version="6.5.0">
<drawing>
<settings>
<setting alwaysvectorfont="yes"/>
<setting verticaltext="up"/>
</settings>
<grid distance="0.1" unitdist="inch" unit="inch" style="lines" multiple="1" display="no" altdistance="0.01" altunitdist="inch" altunit="inch"/>
<layers>
<layer number="1" name="Top" color="4" fill="1" visible="no" active="no"/>
<layer number="2" name="Route2" color="1" fill="3" visible="no" active="no"/>
<layer number="3" name="Route3" color="4" fill="3" visible="no" active="no"/>
<layer number="4" name="Route4" color="1" fill="4" visible="no" active="no"/>
<layer number="5" name="Route5" color="4" fill="4" visible="no" active="no"/>
<layer number="6" name="Route6" color="1" fill="8" visible="no" active="no"/>
<layer number="7" name="Route7" color="4" fill="8" visible="no" active="no"/>
<layer number="8" name="Route8" color="1" fill="2" visible="no" active="no"/>
<layer number="9" name="Route9" color="4" fill="2" visible="no" active="no"/>
<layer number="10" name="Route10" color="1" fill="7" visible="no" active="no"/>
<layer number="11" name="Route11" color="4" fill="7" visible="no" active="no"/>
<layer number="12" name="Route12" color="1" fill="5" visible="no" active="no"/>
<layer number="13" name="Route13" color="4" fill="5" visible="no" active="no"/>
<layer number="14" name="Route14" color="1" fill="6" visible="no" active="no"/>
<layer number="15" name="Route15" color="4" fill="6" visible="no" active="no"/>
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
<library name="relay">
<packages>
<package name="T92-PCPIN">
<pad name="2A" x="0" y="0" drill="2.413"/>
<pad name="4A" x="7.8486" y="0" drill="2.413"/>
<pad name="2B" x="0" y="-5.1562" drill="2.413"/>
<pad name="4B" x="7.8486" y="-5.1562" drill="2.413"/>
<pad name="6A" x="0" y="-16.002" drill="2.413"/>
<pad name="6B" x="0" y="-21.1582" drill="2.413"/>
<pad name="8A" x="7.8486" y="-16.002" drill="2.413"/>
<pad name="8B" x="7.8486" y="-21.1582" drill="2.413"/>
<pad name="0A" x="38.5318" y="2.1336" drill="2.413"/>
<pad name="0B" x="43.688" y="2.1336" drill="2.413"/>
<pad name="1A" x="38.5318" y="-23.2664" drill="2.413"/>
<pad name="1B" x="43.688" y="-23.2664" drill="2.413"/>
<wire x1="-4.1148" y1="6.6802" x2="48.2092" y2="6.6802" width="0.127" layer="21"/>
<wire x1="48.2092" y1="6.6802" x2="48.2092" y2="-27.8638" width="0.127" layer="21"/>
<wire x1="48.2092" y1="-27.8638" x2="-4.1148" y2="-27.8638" width="0.127" layer="21"/>
<wire x1="-4.1148" y1="-27.8638" x2="-4.1148" y2="6.6802" width="0.127" layer="21"/>
<text x="-5.1054" y="-27.8638" size="1.27" layer="21" ratio="10" rot="R90">&gt;NAME</text>
</package>
</packages>
<symbols>
<symbol name="T92S7D12">
<pin name="2" x="-2.54" y="7.62" visible="off" length="short" swaplevel="1" rot="R270"/>
<pin name="4" x="0" y="7.62" visible="off" length="short" swaplevel="2" rot="R270"/>
<pin name="0" x="7.62" y="7.62" visible="off" length="short" swaplevel="3" rot="R270"/>
<pin name="6" x="-2.54" y="-10.16" visible="off" length="short" swaplevel="1" rot="R90"/>
<pin name="8" x="0" y="-10.16" visible="off" length="short" swaplevel="2" rot="R90"/>
<pin name="1" x="7.62" y="-10.16" visible="off" length="short" swaplevel="3" rot="R90"/>
<wire x1="0" y1="5.08" x2="0" y2="2.54" width="0.254" layer="94"/>
<wire x1="0" y1="2.54" x2="1.27" y2="0" width="0.254" layer="94"/>
<wire x1="0" y1="-7.62" x2="0" y2="-5.08" width="0.254" layer="94"/>
<wire x1="0" y1="-5.08" x2="1.27" y2="-2.54" width="0.254" layer="94"/>
<wire x1="-2.54" y1="-7.62" x2="-2.54" y2="-5.08" width="0.254" layer="94"/>
<wire x1="-2.54" y1="-5.08" x2="-0.635" y2="-5.08" width="0.254" layer="94"/>
<wire x1="-2.54" y1="5.08" x2="-2.54" y2="2.54" width="0.254" layer="94"/>
<wire x1="-2.54" y1="2.54" x2="-0.635" y2="2.54" width="0.254" layer="94"/>
<wire x1="-0.635" y1="2.54" x2="-1.27" y2="3.175" width="0.254" layer="94"/>
<wire x1="-0.635" y1="2.54" x2="-1.27" y2="1.905" width="0.254" layer="94"/>
<wire x1="-0.635" y1="-5.08" x2="-1.27" y2="-4.445" width="0.254" layer="94"/>
<wire x1="-0.635" y1="-5.08" x2="-1.27" y2="-5.715" width="0.254" layer="94"/>
<wire x1="7.62" y1="5.08" x2="7.62" y2="1.27" width="0.254" layer="94"/>
<wire x1="7.62" y1="1.27" x2="8.89" y2="0" width="0.254" layer="94"/>
<wire x1="8.89" y1="0" x2="6.35" y2="-2.54" width="0.254" layer="94"/>
<wire x1="6.35" y1="-2.54" x2="7.62" y2="-3.81" width="0.254" layer="94"/>
<wire x1="7.62" y1="-3.81" x2="7.62" y2="-7.62" width="0.254" layer="94"/>
<text x="10.16" y="2.54" size="1.27" layer="95" ratio="10">&gt;NAME</text>
<text x="10.16" y="0" size="1.27" layer="96" ratio="10">&gt;VALUE</text>
</symbol>
</symbols>
<devicesets>
<deviceset name="T92S7D12" prefix="K" uservalue="yes">
<gates>
<gate name="G$1" symbol="T92S7D12" x="0" y="0" swaplevel="3"/>
</gates>
<devices>
<device name="" package="T92-PCPIN">
<connects>
<connect gate="G$1" pin="0" pad="0A 0B"/>
<connect gate="G$1" pin="1" pad="1A 1B"/>
<connect gate="G$1" pin="2" pad="2A 2B"/>
<connect gate="G$1" pin="4" pad="4A 4B"/>
<connect gate="G$1" pin="6" pad="6A 6B"/>
<connect gate="G$1" pin="8" pad="8A 8B"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="con-wago-500">
<description>&lt;b&gt;Wago Screw Clamps&lt;/b&gt;&lt;p&gt;
Grid 5.00 mm&lt;p&gt;
&lt;author&gt;Created by librarian@cadsoft.de&lt;/author&gt;</description>
<packages>
<package name="W237-4">
<description>&lt;b&gt;WAGO SCREW CLAMP&lt;/b&gt;</description>
<wire x1="-8.491" y1="-2.286" x2="-6.484" y2="-0.279" width="0.254" layer="51"/>
<wire x1="-3.512" y1="-2.261" x2="-1.531" y2="-0.254" width="0.254" layer="51"/>
<wire x1="1.517" y1="-2.286" x2="3.524" y2="-0.279" width="0.254" layer="51"/>
<wire x1="6.495" y1="-2.261" x2="8.477" y2="-0.254" width="0.254" layer="51"/>
<wire x1="-9.989" y1="-5.461" x2="10.001" y2="-5.461" width="0.1524" layer="21"/>
<wire x1="10.001" y1="3.734" x2="10.001" y2="3.531" width="0.1524" layer="21"/>
<wire x1="10.001" y1="3.734" x2="-9.989" y2="3.734" width="0.1524" layer="21"/>
<wire x1="-9.989" y1="-5.461" x2="-9.989" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-9.989" y1="-3.073" x2="-8.389" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-8.389" y1="-3.073" x2="-6.611" y2="-3.073" width="0.1524" layer="51"/>
<wire x1="-6.611" y1="-3.073" x2="-3.385" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-1.607" y1="-3.073" x2="1.619" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="3.397" y1="-3.073" x2="6.622" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="8.4" y1="-3.073" x2="10.001" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-9.989" y1="-3.073" x2="-9.989" y2="3.531" width="0.1524" layer="21"/>
<wire x1="10.001" y1="-3.073" x2="10.001" y2="-5.461" width="0.1524" layer="21"/>
<wire x1="-9.989" y1="3.531" x2="10.001" y2="3.531" width="0.1524" layer="21"/>
<wire x1="-9.989" y1="3.531" x2="-9.989" y2="3.734" width="0.1524" layer="21"/>
<wire x1="10.001" y1="3.531" x2="10.001" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-3.385" y1="-3.073" x2="-1.607" y2="-3.073" width="0.1524" layer="51"/>
<wire x1="1.619" y1="-3.073" x2="3.397" y2="-3.073" width="0.1524" layer="51"/>
<wire x1="6.622" y1="-3.073" x2="8.4" y2="-3.073" width="0.1524" layer="51"/>
<circle x="-7.5" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="-7.5" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<circle x="-2.4962" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="-2.4962" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<circle x="2.5076" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="2.5076" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<circle x="7.5114" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="7.5114" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<pad name="1" x="-7.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<pad name="2" x="-2.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<pad name="3" x="2.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<pad name="4" x="7.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<text x="-7.6524" y="-5.0292" size="1.27" layer="27" ratio="10">&gt;VALUE</text>
<text x="-8.7446" y="-7.4422" size="1.27" layer="25" ratio="10">&gt;NAME</text>
<text x="-9.532" y="0.635" size="1.27" layer="51" ratio="10">1</text>
<text x="-4.579" y="0.635" size="1.27" layer="51" ratio="10">2</text>
<text x="0.4756" y="0.635" size="1.27" layer="51" ratio="10">3</text>
<text x="5.4286" y="0.635" size="1.27" layer="51" ratio="10">4</text>
</package>
<package name="W237-102">
<description>&lt;b&gt;WAGO SCREW CLAMP&lt;/b&gt;</description>
<wire x1="-3.491" y1="-2.286" x2="-1.484" y2="-0.279" width="0.254" layer="51"/>
<wire x1="1.488" y1="-2.261" x2="3.469" y2="-0.254" width="0.254" layer="51"/>
<wire x1="-4.989" y1="-5.461" x2="4.993" y2="-5.461" width="0.1524" layer="21"/>
<wire x1="4.993" y1="3.734" x2="4.993" y2="3.531" width="0.1524" layer="21"/>
<wire x1="4.993" y1="3.734" x2="-4.989" y2="3.734" width="0.1524" layer="21"/>
<wire x1="-4.989" y1="-5.461" x2="-4.989" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-4.989" y1="-3.073" x2="-3.389" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-3.389" y1="-3.073" x2="-1.611" y2="-3.073" width="0.1524" layer="51"/>
<wire x1="-1.611" y1="-3.073" x2="1.615" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="3.393" y1="-3.073" x2="4.993" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="-4.989" y1="-3.073" x2="-4.989" y2="3.531" width="0.1524" layer="21"/>
<wire x1="4.993" y1="-3.073" x2="4.993" y2="-5.461" width="0.1524" layer="21"/>
<wire x1="-4.989" y1="3.531" x2="4.993" y2="3.531" width="0.1524" layer="21"/>
<wire x1="-4.989" y1="3.531" x2="-4.989" y2="3.734" width="0.1524" layer="21"/>
<wire x1="4.993" y1="3.531" x2="4.993" y2="-3.073" width="0.1524" layer="21"/>
<wire x1="1.615" y1="-3.073" x2="3.393" y2="-3.073" width="0.1524" layer="51"/>
<circle x="-2.5" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="-2.5" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<circle x="2.5038" y="-1.27" radius="1.4986" width="0.1524" layer="51"/>
<circle x="2.5038" y="2.2098" radius="0.508" width="0.1524" layer="21"/>
<pad name="1" x="-2.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<pad name="2" x="2.5" y="-1.27" drill="1.1938" shape="long" rot="R90"/>
<text x="-5.04" y="-7.62" size="1.27" layer="27" ratio="10">&gt;VALUE</text>
<text x="-3.8462" y="-5.0038" size="1.27" layer="25" ratio="10">&gt;NAME</text>
<text x="-4.532" y="0.635" size="1.27" layer="21" ratio="10">1</text>
<text x="0.421" y="0.635" size="1.27" layer="21" ratio="10">2</text>
</package>
</packages>
<symbols>
<symbol name="KL">
<circle x="1.27" y="0" radius="1.27" width="0.254" layer="94"/>
<text x="0" y="0.889" size="1.778" layer="95" rot="R180">&gt;NAME</text>
<pin name="KL" x="5.08" y="0" visible="off" length="short" direction="pas" rot="R180"/>
</symbol>
<symbol name="KL+V">
<circle x="1.27" y="0" radius="1.27" width="0.254" layer="94"/>
<text x="-2.54" y="-3.683" size="1.778" layer="96">&gt;VALUE</text>
<text x="0" y="0.889" size="1.778" layer="95" rot="R180">&gt;NAME</text>
<pin name="KL" x="5.08" y="0" visible="off" length="short" direction="pas" rot="R180"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="W237-4" prefix="X" uservalue="yes">
<description>&lt;b&gt;WAGO SCREW CLAMP&lt;/b&gt;</description>
<gates>
<gate name="-1" symbol="KL" x="0" y="10.16" addlevel="always"/>
<gate name="-2" symbol="KL" x="0" y="5.08" addlevel="always"/>
<gate name="-3" symbol="KL" x="0" y="0" addlevel="always"/>
<gate name="-4" symbol="KL+V" x="0" y="-5.08" addlevel="always"/>
</gates>
<devices>
<device name="" package="W237-4">
<connects>
<connect gate="-1" pin="KL" pad="1"/>
<connect gate="-2" pin="KL" pad="2"/>
<connect gate="-3" pin="KL" pad="3"/>
<connect gate="-4" pin="KL" pad="4"/>
</connects>
<technologies>
<technology name="">
<attribute name="MF" value="" constant="no"/>
<attribute name="MPN" value="" constant="no"/>
<attribute name="OC_FARNELL" value="unknown" constant="no"/>
<attribute name="OC_NEWARK" value="unknown" constant="no"/>
</technology>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="W237-102" prefix="X" uservalue="yes">
<description>&lt;b&gt;WAGO SCREW CLAMP&lt;/b&gt;</description>
<gates>
<gate name="-1" symbol="KL" x="0" y="5.08" addlevel="always"/>
<gate name="-2" symbol="KL+V" x="0" y="0" addlevel="always"/>
</gates>
<devices>
<device name="" package="W237-102">
<connects>
<connect gate="-1" pin="KL" pad="1"/>
<connect gate="-2" pin="KL" pad="2"/>
</connects>
<technologies>
<technology name="">
<attribute name="MF" value="" constant="no"/>
<attribute name="MPN" value="237-102" constant="no"/>
<attribute name="OC_FARNELL" value="unknown" constant="no"/>
<attribute name="OC_NEWARK" value="70K9898" constant="no"/>
</technology>
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
<part name="K1" library="relay" deviceset="T92S7D12" device=""/>
<part name="K2" library="relay" deviceset="T92S7D12" device=""/>
<part name="X1" library="con-wago-500" deviceset="W237-4" device=""/>
<part name="X2" library="con-wago-500" deviceset="W237-4" device=""/>
<part name="X3" library="con-wago-500" deviceset="W237-102" device=""/>
<part name="X4" library="con-wago-500" deviceset="W237-102" device=""/>
</parts>
<sheets>
<sheet>
<plain>
</plain>
<instances>
<instance part="K1" gate="G$1" x="30.48" y="48.26"/>
<instance part="K2" gate="G$1" x="30.48" y="7.62"/>
<instance part="X1" gate="-1" x="10.16" y="55.88"/>
<instance part="X1" gate="-2" x="10.16" y="50.8"/>
<instance part="X1" gate="-3" x="10.16" y="45.72"/>
<instance part="X1" gate="-4" x="10.16" y="40.64"/>
<instance part="X2" gate="-1" x="10.16" y="5.08"/>
<instance part="X2" gate="-2" x="10.16" y="0"/>
<instance part="X2" gate="-3" x="10.16" y="15.24"/>
<instance part="X2" gate="-4" x="10.16" y="10.16"/>
<instance part="X3" gate="-1" x="10.16" y="68.58"/>
<instance part="X3" gate="-2" x="10.16" y="63.5"/>
<instance part="X4" gate="-1" x="10.16" y="27.94"/>
<instance part="X4" gate="-2" x="10.16" y="22.86"/>
</instances>
<busses>
</busses>
<nets>
<net name="N$1" class="0">
<segment>
<pinref part="X1" gate="-1" pin="KL"/>
<wire x1="15.24" y1="55.88" x2="17.78" y2="55.88" width="0.1524" layer="91"/>
<wire x1="17.78" y1="55.88" x2="22.86" y2="55.88" width="0.1524" layer="91"/>
<wire x1="22.86" y1="55.88" x2="22.86" y2="58.42" width="0.1524" layer="91"/>
<wire x1="22.86" y1="58.42" x2="27.94" y2="58.42" width="0.1524" layer="91"/>
<pinref part="K1" gate="G$1" pin="2"/>
<wire x1="27.94" y1="58.42" x2="27.94" y2="55.88" width="0.1524" layer="91"/>
<pinref part="X1" gate="-2" pin="KL"/>
<wire x1="15.24" y1="50.8" x2="17.78" y2="50.8" width="0.1524" layer="91"/>
<wire x1="17.78" y1="50.8" x2="22.86" y2="50.8" width="0.1524" layer="91"/>
<wire x1="22.86" y1="50.8" x2="22.86" y2="35.56" width="0.1524" layer="91"/>
<pinref part="K1" gate="G$1" pin="6"/>
<wire x1="22.86" y1="35.56" x2="27.94" y2="35.56" width="0.1524" layer="91"/>
<wire x1="27.94" y1="35.56" x2="27.94" y2="38.1" width="0.1524" layer="91"/>
<wire x1="17.78" y1="55.88" x2="17.78" y2="50.8" width="0.1524" layer="91"/>
<junction x="17.78" y="55.88"/>
<junction x="17.78" y="50.8"/>
</segment>
</net>
<net name="N$3" class="0">
<segment>
<wire x1="20.32" y1="45.72" x2="20.32" y2="60.96" width="0.1524" layer="91"/>
<pinref part="K1" gate="G$1" pin="4"/>
<wire x1="20.32" y1="60.96" x2="30.48" y2="60.96" width="0.1524" layer="91"/>
<wire x1="30.48" y1="60.96" x2="30.48" y2="55.88" width="0.1524" layer="91"/>
<pinref part="X1" gate="-3" pin="KL"/>
<wire x1="20.32" y1="45.72" x2="17.78" y2="45.72" width="0.1524" layer="91"/>
<pinref part="X1" gate="-4" pin="KL"/>
<wire x1="17.78" y1="45.72" x2="15.24" y2="45.72" width="0.1524" layer="91"/>
<wire x1="15.24" y1="40.64" x2="17.78" y2="40.64" width="0.1524" layer="91"/>
<wire x1="17.78" y1="40.64" x2="20.32" y2="40.64" width="0.1524" layer="91"/>
<wire x1="20.32" y1="40.64" x2="20.32" y2="33.02" width="0.1524" layer="91"/>
<pinref part="K1" gate="G$1" pin="8"/>
<wire x1="20.32" y1="33.02" x2="30.48" y2="33.02" width="0.1524" layer="91"/>
<wire x1="30.48" y1="33.02" x2="30.48" y2="38.1" width="0.1524" layer="91"/>
<wire x1="17.78" y1="45.72" x2="17.78" y2="40.64" width="0.1524" layer="91"/>
<junction x="17.78" y="45.72"/>
<junction x="17.78" y="40.64"/>
</segment>
</net>
<net name="N$5" class="0">
<segment>
<pinref part="X3" gate="-2" pin="KL"/>
<pinref part="K1" gate="G$1" pin="0"/>
<wire x1="15.24" y1="63.5" x2="38.1" y2="63.5" width="0.1524" layer="91"/>
<wire x1="38.1" y1="63.5" x2="38.1" y2="55.88" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$6" class="0">
<segment>
<pinref part="X3" gate="-1" pin="KL"/>
<wire x1="15.24" y1="68.58" x2="43.18" y2="68.58" width="0.1524" layer="91"/>
<wire x1="43.18" y1="68.58" x2="43.18" y2="35.56" width="0.1524" layer="91"/>
<pinref part="K1" gate="G$1" pin="1"/>
<wire x1="43.18" y1="35.56" x2="38.1" y2="35.56" width="0.1524" layer="91"/>
<wire x1="38.1" y1="35.56" x2="38.1" y2="38.1" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$7" class="0">
<segment>
<wire x1="22.86" y1="15.24" x2="22.86" y2="17.78" width="0.1524" layer="91"/>
<pinref part="K2" gate="G$1" pin="2"/>
<wire x1="22.86" y1="17.78" x2="27.94" y2="17.78" width="0.1524" layer="91"/>
<wire x1="27.94" y1="17.78" x2="27.94" y2="15.24" width="0.1524" layer="91"/>
<pinref part="X2" gate="-3" pin="KL"/>
<wire x1="15.24" y1="15.24" x2="17.78" y2="15.24" width="0.1524" layer="91"/>
<wire x1="17.78" y1="15.24" x2="22.86" y2="15.24" width="0.1524" layer="91"/>
<wire x1="22.86" y1="10.16" x2="22.86" y2="-5.08" width="0.1524" layer="91"/>
<pinref part="K2" gate="G$1" pin="6"/>
<wire x1="22.86" y1="-5.08" x2="27.94" y2="-5.08" width="0.1524" layer="91"/>
<wire x1="27.94" y1="-5.08" x2="27.94" y2="-2.54" width="0.1524" layer="91"/>
<pinref part="X2" gate="-4" pin="KL"/>
<wire x1="22.86" y1="10.16" x2="17.78" y2="10.16" width="0.1524" layer="91"/>
<wire x1="17.78" y1="10.16" x2="15.24" y2="10.16" width="0.1524" layer="91"/>
<wire x1="17.78" y1="15.24" x2="17.78" y2="10.16" width="0.1524" layer="91"/>
<junction x="17.78" y="15.24"/>
<junction x="17.78" y="10.16"/>
</segment>
</net>
<net name="N$9" class="0">
<segment>
<wire x1="20.32" y1="5.08" x2="20.32" y2="20.32" width="0.1524" layer="91"/>
<pinref part="K2" gate="G$1" pin="4"/>
<wire x1="20.32" y1="20.32" x2="30.48" y2="20.32" width="0.1524" layer="91"/>
<wire x1="30.48" y1="20.32" x2="30.48" y2="15.24" width="0.1524" layer="91"/>
<pinref part="X2" gate="-1" pin="KL"/>
<wire x1="15.24" y1="5.08" x2="17.78" y2="5.08" width="0.1524" layer="91"/>
<wire x1="17.78" y1="5.08" x2="20.32" y2="5.08" width="0.1524" layer="91"/>
<wire x1="20.32" y1="0" x2="20.32" y2="-7.62" width="0.1524" layer="91"/>
<pinref part="K2" gate="G$1" pin="8"/>
<wire x1="20.32" y1="-7.62" x2="30.48" y2="-7.62" width="0.1524" layer="91"/>
<wire x1="30.48" y1="-7.62" x2="30.48" y2="-2.54" width="0.1524" layer="91"/>
<pinref part="X2" gate="-2" pin="KL"/>
<wire x1="20.32" y1="0" x2="17.78" y2="0" width="0.1524" layer="91"/>
<wire x1="17.78" y1="0" x2="15.24" y2="0" width="0.1524" layer="91"/>
<wire x1="17.78" y1="5.08" x2="17.78" y2="0" width="0.1524" layer="91"/>
<junction x="17.78" y="5.08"/>
<junction x="17.78" y="0"/>
</segment>
</net>
<net name="N$11" class="0">
<segment>
<pinref part="K2" gate="G$1" pin="0"/>
<wire x1="38.1" y1="22.86" x2="38.1" y2="15.24" width="0.1524" layer="91"/>
<pinref part="X4" gate="-2" pin="KL"/>
<wire x1="38.1" y1="22.86" x2="15.24" y2="22.86" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$12" class="0">
<segment>
<wire x1="43.18" y1="27.94" x2="43.18" y2="-5.08" width="0.1524" layer="91"/>
<pinref part="K2" gate="G$1" pin="1"/>
<wire x1="43.18" y1="-5.08" x2="38.1" y2="-5.08" width="0.1524" layer="91"/>
<wire x1="38.1" y1="-5.08" x2="38.1" y2="-2.54" width="0.1524" layer="91"/>
<pinref part="X4" gate="-1" pin="KL"/>
<wire x1="15.24" y1="27.94" x2="43.18" y2="27.94" width="0.1524" layer="91"/>
</segment>
</net>
</nets>
</sheet>
</sheets>
</schematic>
</drawing>
</eagle>
