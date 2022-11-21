package vnu.uet.prodmove.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    @Column(length = 77)
    private String pictureUrlSmall;

    @Column(length = 370)
    private String pictureUrlBig;

    @Column(length = 4)
    private String fans;

    @Column(length = 15)
    private String hits;

    @Column(length = 4)
    private String hitsPercent;

    @Column(length = 36)
    private String networkTechology;

    @Column(length = 115)
    private String network2GBands;

    @Column(length = 155)
    private String network3GBands;

    @Column(length = 518)
    private String network4GBands;

    @Column(length = 254)
    private String network5GBands;

    @Column(length = 116)
    private String networkSpeed;

    @Column(length = 4)
    private String networkGprs;

    @Column(length = 4)
    private String networkEdge;

    @Column(length = 49)
    private String announced;

    @Column(length = 39)
    private String status;

    @Column(length = 48)
    private String bodyDimensions;

    @Column(length = 46)
    private String bodyWeight;

    @Column(length = 154)
    private String bodyBuilt;

    @Column(length = 10)
    private String bodyKeyboard;

    @Column(length = 84)
    private String bodySim;

    @Column(length = 118)
    private String bodyOther;

    @Column(length = 126)
    private String displayType;

    @Column(length = 53)
    private String displaySize;

    @Column(length = 52)
    private String displayResolution;

    @Column(length = 10)
    private String displayMultitouch;

    @Column(length = 52)
    private String displayProtection;

    @Column(length = 94)
    private String displayOther;

    @Column(length = 85)
    private String platformOs;

    @Column(length = 84)
    private String platformChipset;

    @Column(length = 181)
    private String platformCpu;

    @Column(length = 55)
    private String platformGpu;

    @Column(length = 55)
    private String memoryCardSlot;

    @Column(length = 16)
    private String memoryPhonebook;

    @Column(length = 72)
    private String memoryInternal;

    @Column(length = 41)
    private String memoryCallRecords;

    @Column(length = 64)
    private String mainCameraSingle;

    @Column(length = 141)
    private String mainCameraDual;

    @Column(length = 213)
    private String mainCameraTriple;

    @Column(length = 238)
    private String mainCameraQuad;

    @Column(length = 10)
    private String mainCameraFive;

    @Column(length = 10)
    private String mainCameraSix;

    @Column(length = 62)
    private String mainCameraFeatures;

    @Column(length = 105)
    private String mainCameraVideo;

    @Column(length = 59)
    private String selfieCameraSingle;

    @Column(length = 83)
    private String selfieCameraDual;

    @Column(length = 10)
    private String selfieCameraTriple;

    @Column(length = 10)
    private String selfieCameraQuad;

    @Column(length = 53)
    private String selfieCameraFeatures;

    @Column(length = 45)
    private String selfieCameraVideo;

    @Column(length = 55)
    private String soundAlertTypes;

    @Column(length = 39)
    private String soundLoudspeaker;

    @Column(length = 4)
    private String sound5mmJack;

    @Column(length = 38)
    private String soundOther;

    @Column(length = 64)
    private String commsWlan;

    @Column(length = 33)
    private String commsBluetooth;

    @Column(length = 76)
    private String commsGps;

    @Column(length = 21)
    private String commsNfc;

    @Column(length = 60)
    private String commsRadio;

    @Column(length = 62)
    private String commsUsb;

    @Column(length = 112)
    private String featuresSensors;

    @Column(length = 21)
    private String featuresMessaging;

    @Column(length = 24)
    private String featuresBrowser;

    @Column(length = 4)
    private String featuresClock;

    @Column(length = 4)
    private String featuresAlarm;

    @Column(length = 45)
    private String featuresGames;

    @Column(length = 21)
    private String featuresLanguages;

    @Column(length = 3)
    private String featuresJava;

    @Column(length = 175)
    private String featuresOther;

    @Column(length = 48)
    private String batteryOther;

    @Column(length = 110)
    private String batteryCharging;

    @Column(length = 38)
    private String batteryStandBy;

    @Column(length = 54)
    private String batteryTalkTime;

    @Column(length = 12)
    private String batteryMusicPlay;

    @Column(length = 230)
    private String miscColors;

    @Column(length = 233)
    private String miscModels;

    @Column(length = 63)
    private String miscSarUs;

    @Column(length = 63)
    private String miscSarEu;

    @Column(length = 96)
    private String miscPriceGroup;

    @Column(length = 108)
    private String testsPerformance;

    @Column(length = 58)
    private String testsDisplay;

    @Column(length = 16)
    private String testsCamera;

    @Column(length = 42)
    private String testsLoudspeaker;

    @Column(length = 36)
    private String testsAudioQuality;

    @Column(length = 30)
    private String testsBatteryLife;

    @OneToMany(mappedBy = "productline")
    private Set<Product> productlineProducts;

}
