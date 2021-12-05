package com.example.coreapi.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class QuoteResponse(
    val securities: Security,
    val marketdata: MarketData,
)

@JsonClass(generateAdapter = true)
class Security(
    val columns: List<SecurityColumn>,
    val data: List<List<Any>>
)

@JsonClass(generateAdapter = true)
class MarketData(
    val columns: List<MarketdataColumn>,
    val data: List<List<Any>>
)

enum class SecurityColumn {
    SECID,
    BOARDID,
    SHORTNAME,
    PREVPRICE,
    LOTSIZE,
    FACEVALUE,
    STATUS,
    BOARDNAME,
    DECIMALS,
    SECNAME,
    REMARKS,
    MARKETCODE,
    INSTRID,
    SECTORID,
    MINSTEP,
    PREVWAPRICE,
    FACEUNIT,
    PREVDATE,
    ISSUESIZE,
    ISIN,
    LATNAME,
    REGNUMBER,
    PREVLEGALCLOSEPRICE,
    PREVADMITTEDQUOTE,
    CURRENCYID,
    SECTYPE,
    LISTLEVEL,
    SETTLEDATE
}

enum class MarketdataColumn {
    SECID,
    BOARDID,
    BID,
    BIDDEPTH,
    OFFER,
    OFFERDEPTH,
    SPREAD,
    BIDDEPTHT,
    OFFERDEPTHT,
    OPEN,
    LOW,
    HIGH,
    LAST,
    LASTCHANGE,
    LASTCHANGEPRCNT,
    QTY,
    VALUE,
    VALUE_USD,
    WAPRICE,
    LASTCNGTOLASTWAPRICE,
    WAPTOPREVWAPRICEPRCNT,
    WAPTOPREVWAPRICE,
    CLOSEPRICE,
    MARKETPRICETODAY,
    MARKETPRICE,
    LASTTOPREVPRICE,
    NUMTRADES,
    VOLTODAY,
    VALTODAY,
    VALTODAY_USD,
    ETFSETTLEPRICE,
    TRADINGSTATUS,
    UPDATETIME,
    ADMITTEDQUOTE,
    LASTBID,
    LASTOFFER,
    LCLOSEPRICE,
    LCURRENTPRICE,
    MARKETPRICE2,
    NUMBIDS,
    NUMOFFERS,
    CHANGE,
    TIME,
    HIGHBID,
    LOWOFFER,
    PRICEMINUSPREVWAPRICE,
    OPENPERIODPRICE,
    SEQNUM,
    SYSTIME,
    CLOSINGAUCTIONPRICE,
    CLOSINGAUCTIONVOLUME,
    ISSUECAPITALIZATION,
    ISSUECAPITALIZATION_UPDATETIME,
    ETFSETTLECURRENCY,
    VALTODAY_RUR,
    TRADINGSESSION,
}
