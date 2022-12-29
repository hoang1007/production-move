package vnu.uet.prodmove.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productline")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Productline {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8)
    private String brand;

    @Column(length = 34)
    private String phone;

    @Column(length = 277)
    private String notes;

    @Column(length = 77, name = "\"Picture URL small\"")
    private String pictureUrlSmall;

    @Column(length = 370, name = "\"Picture URL big\"")
    private String pictureUrlBig;

    @Column(length = 4)
    private String fans;

    @Column(length = 15)
    private String hits;

    @Column(length = 4, name = "\"Hits percent\"")
    private String hitsPercent;

    @Column(length = 36, name = "\"Network Technology\"")
    private String networkTechology;

    @Column(length = 115, name = "\"Network 2G bands\"")
    private String network2GBands;

    @Column(length = 155, name = "\"Network 3G bands\"")
    private String network3GBands;

    @Column(length = 518, name = "\"Network 4G bands\"")
    private String network4GBands;

    @Column(length = 254, name = "\"Network 5G bands\"")
    private String network5GBands;

    @Column(length = 116, name = "\"Network Speed\"")
    private String networkSpeed;

    @Column(length = 4, name = "\"Network GPRS\"")
    private String networkGprs;

    @Column(length = 4, name = "\"Network EDGE\"")
    private String networkEdge;

    @Column(length = 49)
    private String announced;

    @Column(length = 39)
    private String status;

    @Column(length = 48, name = "\"Body Dimensions\"")
    private String bodyDimensions;

    @Column(length = 46, name = "\"Body Weight\"")
    private String bodyWeight;

    @Column(length = 154, name = "\"Body Built\"")
    private String bodyBuilt;

    @Column(length = 10, name = "\"Body Keyboard\"")
    private String bodyKeyboard;

    @Column(length = 84, name = "\"Body SIM\"")
    private String bodySim;

    @Column(length = 118, name = "\"Body other\"")
    private String bodyOther;

    @Column(length = 126, name = "\"Display Type\"")
    private String displayType;

    @Column(length = 53, name = "\"Display Size\"")
    private String displaySize;

    @Column(length = 52, name = "\"Display Resolution\"")
    private String displayResolution;

    @Column(length = 10, name = "\"Display Multitouch\"")
    private String displayMultitouch;

    @Column(length = 52, name = "\"Display Protection\"")
    private String displayProtection;

    @Column(length = 94, name = "\"Display other\"")
    private String displayOther;

    @Column(length = 85, name = "\"Platform OS\"")
    private String platformOs;

    @Column(length = 84, name = "\"Platform Chipset\"")
    private String platformChipset;

    @Column(length = 181, name = "\"Platform CPU\"")
    private String platformCpu;

    @Column(length = 55, name = "\"Platform GPU\"")
    private String platformGpu;

    @Column(length = 55, name = "\"Memory Card slot\"")
    private String memoryCardSlot;

    @Column(length = 16, name = "\"Memory Phonebook\"")
    private String memoryPhonebook;

    @Column(length = 72, name = "\"Memory Internal\"")
    private String memoryInternal;

    @Column(length = 41, name = "\"Memory Call records\"")
    private String memoryCallRecords;

    @Column(length = 64, name = "\"Main Camera Single\"")
    private String mainCameraSingle;

    @Column(length = 141, name = "\"Main Camera Dual\"")
    private String mainCameraDual;

    @Column(length = 213, name = "\"Main Camera Triple\"")
    private String mainCameraTriple;

    @Column(length = 238, name = "\"Main Camera Quad\"")
    private String mainCameraQuad;

    @Column(length = 10, name = "\"Main Camera Five\"")
    private String mainCameraFive;

    @Column(length = 10, name = "\"Main Camera Six\"")
    private String mainCameraSix;

    @Column(length = 62, name = "\"Main Camera Features\"")
    private String mainCameraFeatures;

    @Column(length = 105, name = "\"Main Camera Video\"")
    private String mainCameraVideo;

    @Column(length = 59, name = "\"Selfie Camera Single\"")
    private String selfieCameraSingle;

    @Column(length = 83, name = "\"Selfie Camera Dual\"")
    private String selfieCameraDual;

    @Column(length = 10, name = "\"Selfie Camera Triple\"")
    private String selfieCameraTriple;

    @Column(length = 10, name = "\"Selfie Camera Quad\"")
    private String selfieCameraQuad;

    @Column(length = 53, name = "\"Selfie Camera Features\"")
    private String selfieCameraFeatures;

    @Column(length = 45, name = "\"Selfie Camera Video\"")
    private String selfieCameraVideo;

    @Column(length = 55, name = "\"Sound Alert types\"")
    private String soundAlertTypes;

    @Column(length = 39, name = "\"Sound Loudspeaker\"")
    private String soundLoudspeaker;

    @Column(length = 4, name = "\"Sound 3.5mm jack\"")
    private String sound3dot5mmJack;

    @Column(length = 38, name = "\"Sound Other\"")
    private String soundOther;

    @Column(length = 64, name = "\"Comms WLAN\"")
    private String commsWlan;

    @Column(length = 33, name = "\"Comms Bluetooth\"")
    private String commsBluetooth;

    @Column(length = 76, name = "\"Comms GPS\"")
    private String commsGps;

    @Column(length = 21, name = "\"Comms NFC\"")
    private String commsNfc;

    @Column(length = 60, name = "\"Comms Radio\"")
    private String commsRadio;

    @Column(length = 62, name = "\"Comms USB\"")
    private String commsUsb;

    @Column(length = 112, name = "\"Features Sensors\"")
    private String featuresSensors;

    @Column(length = 21, name = "\"Features Messaging\"")
    private String featuresMessaging;

    @Column(length = 24, name = "\"Features Browser\"")
    private String featuresBrowser;

    @Column(length = 4, name = "\"Features Clock\"")
    private String featuresClock;

    @Column(length = 4, name = "\"Features Alarm\"")
    private String featuresAlarm;

    @Column(length = 45, name = "\"Features Games\"")
    private String featuresGames;

    @Column(length = 21, name = "\"Features Languages\"")
    private String featuresLanguages;

    @Column(length = 3, name = "\"Features Java\"")
    private String featuresJava;

    @Column(length = 175, name = "\"Features other\"")
    private String featuresOther;

    @Column(length = 48, name = "\"Battery other\"")
    private String batteryOther;

    @Column(length = 110, name = "\"Battery Charging\"")
    private String batteryCharging;

    @Column(length = 38, name = "\"Battery Stand by\"")
    private String batteryStandBy;

    @Column(length = 54, name = "\"Battery Talk time\"")
    private String batteryTalkTime;

    @Column(length = 12, name = "\"Battery Music play\"")
    private String batteryMusicPlay;

    @Column(length = 230, name = "\"Misc Colors\"")
    private String miscColors;

    @Column(length = 233, name = "\"Misc Models\"")
    private String miscModels;

    @Column(length = 63, name = "\"Misc SAR US\"")
    private String miscSarUs;

    @Column(length = 63, name = "\"Misc SAR EU\"")
    private String miscSarEu;

    @Column(length = 96, name = "\"Misc Price group\"")
    private String miscPriceGroup;

    @Column(length = 108, name = "\"Tests Performance\"")
    private String testsPerformance;

    @Column(length = 58, name = "\"Tests Display\"")
    private String testsDisplay;

    @Column(length = 16, name = "\"Tests Camera\"")
    private String testsCamera;

    @Column(length = 42, name = "\"Tests Loudspeaker\"")
    private String testsLoudspeaker;

    @Column(length = 36, name = "\"Tests Audio quality\"")
    private String testsAudioQuality;

    @Column(length = 30, name = "\"Tests Battery life\"")
    private String testsBatteryLife;

    @OneToMany(mappedBy = "productline")
    @JsonBackReference
    private Set<Product> products;

}