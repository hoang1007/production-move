export interface Action {
    type: string,
    payload: any
}

export interface AgencyType {
    id: number,
    name: string,
    address: string,
}

export interface WarehouseType {
    id: number,
    address: string,
    products: ProductType[]
}

export interface WarrantyCenter {
    id: number,
    name: string,
    address: string,
}

export interface ProductDetailType {
    id: number,
    stage: string,
    startAt: Date,
    endAt?: Date,
    description?: string,
    product: ProductType,
    warrantyCenter: AgencyType,
}

export interface BatchProductType {
    id: number;
    name: string;
    quantity: number;
    importDate: Date;
}

export interface ProductType {
    id: number,
    productline: ProductLineType,
    productDetails: ProductDetailType[],
}

export interface ProductLineType {
    "id": number,
    "brand": string,
    "phone": string,
    "notes"?: string,
    "pictureUrlSmall"?: string,
    "pictureUrlBig"?: string,
    "fans"?: string,
    "hits"?: string,
    "hitsPercent"?: string,
    "networkTechology"?: string,
    "network2GBands"?: string,
    "network3GBands"?: string,
    "network4GBands"?: string,
    "network5GBands"?: string,
    "networkSpeed"?: string,
    "networkGprs"?: string,
    "networkEdge"?: string,
    "announced"?: string,
    "status"?: string,
    "bodyDimensions"?: string,
    "bodyWeight"?: string,
    "bodyBuilt"?: string,
    "bodyKeyboard"?: string,
    "bodySim"?: null,
    "bodyOther"?: string,
    "displayType"?: string,
    "displaySize"?: string,
    "displayResolution"?: string,
    "displayMultitouch"?: string,
    "displayProtection"?: string,
    "displayOther"?: string,
    "platformOs"?: string,
    "platformChipset"?: string,
    "platformCpu"?: string,
    "platformGpu"?: string,
    "memoryCardSlot"?: string,
    "memoryPhonebook"?: string,
    "memoryInternal"?: string,
    "memoryCallRecords"?: string,
    "mainCameraSingle"?: string,
    "mainCameraDual"?: string,
    "mainCameraTriple"?: string,
    "mainCameraQuad"?: string,
    "mainCameraFive"?: string,
    "mainCameraSix"?: string,
    "mainCameraFeatures"?: string,
    "mainCameraVideo"?: string,
    "selfieCameraSingle"?: string,
    "selfieCameraDual"?: string,
    "selfieCameraTriple"?: string,
    "selfieCameraQuad"?: string,
    "selfieCameraFeatures"?: string,
    "selfieCameraVideo"?: string,
    "soundAlertTypes"?: string,
    "soundLoudspeaker"?: string,
    "sound5mmJack"?: string,
    "soundOther"?: string,
    "commsWlan"?: string,
    "commsBluetooth"?: string,
    "commsGps"?: string,
    "commsNfc"?: string,
    "commsRadio"?: string,
    "commsUsb"?: string,
    "featuresSensors"?: string,
    "featuresMessaging"?: string,
    "featuresBrowser"?: string,
    "featuresClock"?: string,
    "featuresAlarm"?: string,
    "featuresGames"?: string,
    "featuresLanguages"?: string,
    "featuresJava"?: string,
    "featuresOther"?: string,
    "batteryOther"?: string,
    "batteryCharging"?: string,
    "batteryStandBy"?: string,
    "batteryTalkTime"?: string,
    "batteryMusicPlay"?: string,
    "miscColors"?: string,
    "miscModels"?: string,
    "miscSarUs"?: string,
    "miscSarEu"?: string,
    "miscPriceGroup"?: string,
    "testsPerformance"?: string,
    "testsDisplay"?: string,
    "testsCamera"?: string,
    "testsLoudspeaker"?: string,
    "testsAudioQuality"?: string,
    "testsBatteryLife"?: string,
    [key: string]: string | number | null | undefined
}

export interface CustomerType {
    id: number,
    fullname: string,
    email: string,
    phoneNumber?: string,
    orders: OrderType[]
}

export interface OrderType {
    id: number,
    orderDate: Date,
    soldDate: Date,
    product: ProductType
}

///// admin

export interface AccountType {
    username: string,
    password: string, // just empty string
    role: string,
    user: {
        id: number,
        name: string,
        address: string
    }
}