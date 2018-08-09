package com.work.hany.playinseoul.util;

public class ConverterUtils {
    /** 컨텐츠타입 */
    public static String convertContentType(int code) {
        String type;
        switch (code) {
            case 12: type = "관광지"; break;
            case 14: type = "문화시설"; break;
            case 15: type = "축제/공연/행사"; break;
            case 25: type = "여행코스"; break;
            case 28: type = "레포츠"; break;
            case 32: type = "숙박"; break;
            case 38: type = "쇼핑"; break;
            case 39: type = "음식"; break;
            default: type = String.valueOf(code); break;
        } return type;
    }



    /** 대분류 */
    public static String convertLargeCategory(String code) {
        if(code == null) return "";
        switch (code) {
            case "A01": code = "자연"; break;
            case "A02": code = "인문(문화/예술/역사)"; break;
            case "C01": code = "추천코스"; break;
            case "A03": code = "레포츠"; break;
            case "B02": code = "숙박시설"; break;
            case "A04": code = "쇼핑"; break;
            case "A05": code = "음식점"; break;
        } return code;
    }


    /** 중분류 */
    public static String convertMediumCategory(String code) {
        if(code == null) return "";
        switch (code) {
            case "A0101": code = "자연관광지"; break;
            case "A0102": code = "관광자원"; break;
            case "A0201": code = "역사관광지"; break;
            case "A0202": code = "휴양관광지"; break;
            case "A0203": code = "체험관광지"; break;
            case "A0204": code = "산업관광지"; break;
            case "A0205": code = "건축/조형물"; break;
            case "A0206": code = "문화시설"; break;
            case "A0207": code = "축제"; break;
            case "A0208": code = "공연/행사"; break;
            case "C0112": code = "가족코스"; break;
            case "C0113": code = "나홀로코스"; break;
            case "C0114": code = "힐링코스"; break;
            case "C0115": code = "도보코스"; break;
            case "C0116": code = "캠핑코스"; break;
            case "C0117": code = "맛코스"; break;
            case "A0301": code = "레포츠소개"; break;
            case "A0302": code = "육상레포츠"; break;
            case "A0303": code = "수상레포츠"; break;
            case "A0304": code = "항공레포츠"; break;
            case "A0305": code = "복합레포츠"; break;
            case "B0201": code = "숙박시설"; break;
            case "A0401": code = "쇼핑"; break;
            case "A0502": code = "음식점"; break;
        } return code;
    }

    /** 소분류 */
    public static String convertSmallCategory(String code) {
        if(code == null) return "";
        switch (code) {
            case "C01120001": code = "가족코스"; break; // 여행코스
            case "C01130001": code = "나홀로코스"; break;
            case "C01140001": code = "힐링코스"; break;
            case "C01150001": code = "도보코스"; break;
            case "C01160001": code = "캠핑코스"; break;
            case "C01170001": code = "맛코스"; break; // 여행코스 끝

            case "A01010100": code = "국립공원"; break; // 관광지 시작
            case "A01010200": code = "도립공원"; break;
            case "A01010300": code = "군립공원"; break;
            case "A01010400": code = "산"; break;
            case "A01010500": code = "자연생태관광지"; break;
            case "A01010600": code = "자연휴양림"; break;
            case "A01010700": code = "수목원"; break;
            case "A01010800": code = "폭포"; break;
            case "A01010900": code = "계곡"; break;
            case "A01011000": code = "약수터"; break;
            case "A01011100": code = "해안절경"; break;
            case "A01011200": code = "해수욕장"; break;
            case "A01011300": code = "섬"; break;
            case "A01011400": code = "항구/포구"; break;
            case "A01011500": code = "어촌"; break;
            case "A01011600": code = "등대"; break;
            case "A01011700": code = "호수"; break;
            case "A01011800": code = "강"; break;
            case "A01011900": code = "동굴"; break;
            case "A01020100": code = "희귀/동식물"; break;
            case "A01020200": code = "기암괴석"; break;
            case "A02010100": code = "고궁"; break;
            case "A02010200": code = "성"; break;
            case "A02010300": code = "문"; break;
            case "A02010400": code = "고택"; break;
            case "A02010500": code = "생가"; break;
            case "A02010600": code = "민속마을"; break;
            case "A02010700": code = "유적지/사적지"; break;
            case "A02010800": code = "사찰"; break;
            case "A02010900": code = "종교성지"; break;
            case "A02011000": code = "안보관광"; break;
            case "A02020100": code = "유원지"; break;
            case "A02020200": code = "관광단지"; break;
            case "A02020300": code = "온천/욕장/스파"; break;
            case "A02020400": code = "이색찜질방"; break;
            case "A02020500": code = "헬스투어"; break;
            case "A02020600": code = "테마공원"; break;
            case "A02020700": code = "공원"; break;
            case "A02020800": code = "유람선/잠수함관광"; break;
            case "A02030100": code = "농/산/어촌체험"; break;
            case "A02030200": code = "전통체험"; break;
            case "A02030300": code = "산사체험"; break;
            case "A02030400": code = "이색체험"; break;
            case "A02030500": code = "관광농원"; break;
            case "A02030600": code = "이색거리"; break;
            case "A02040100": code = "제철소"; break;
            case "A02040200": code = "조선소"; break;
            case "A02040300": code = "공단"; break;
            case "A02040400": code = "발전소"; break;
            case "A02040500": code = "광산"; break;
            case "A02040600": code = "식음료"; break;
            case "A02040700": code = "화학/금속"; break;
            case "A02040800": code = "기타"; break;
            case "A02040900": code = "전자/반도체"; break;
            case "A02041000": code = "자동차"; break;
            case "A02050100": code = "다리/대교"; break;
            case "A02050200": code = "기념탑/기념비/전망대"; break;
            case "A02050300": code = "분수"; break;
            case "A02050400": code = "동상"; break;
            case "A02050500": code = "터널"; break;
            case "A02050600": code = "유명건물"; break;// 관광지 끝

            case "A02060100": code = "박물관"; break;// 문화시설 시작
            case "A02060200": code = "기념관"; break;
            case "A02060300": code = "전시관"; break;
            case "A02060400": code = "컨벤션센터"; break;
            case "A02060500": code = "미술관/화랑"; break;
            case "A02060600": code = "공연장"; break;
            case "A02060700": code = "문화원"; break;
            case "A02060800": code = "외국문화원"; break;
            case "A02060900": code = "도서관"; break;
            case "A02061000": code = "대형서점"; break;
            case "A02061100": code = "문화전수시설"; break;
            case "A02061200": code = "영화관"; break;
            case "A02061300": code = "어학당"; break;
            case "A02061400": code = "학교"; break;// 문화시설 끝

            case "A02070100": code = "문화관광축제"; break;// 축제/공연/행사 시작
            case "A02070200": code = "일반축제"; break;
            case "A02080100": code = "전통공연"; break;
            case "A02080200": code = "연극"; break;
            case "A02080300": code = "뮤지컬"; break;
            case "A02080400": code = "오페라"; break;
            case "A02080500": code = "전시회"; break;
            case "A02080600": code = "박람회"; break;
            case "A02080700": code = "컨벤션"; break;
            case "A02080800": code = "무용"; break;
            case "A02080900": code = "클래식음악회"; break;
            case "A02081000": code = "대중콘서트"; break;
            case "A02081100": code = "영화"; break;
            case "A02081200": code = "스포츠경기"; break;
            case "A02081300": code = "기타행사"; break;// 축제/공연/행사 끝

            case "A03010100": code = "육상레포츠"; break;// 레포츠 시작
            case "A03010200": code = "수상레포츠"; break;
            case "A03010300": code = "항공레포츠"; break;
            case "A03020100": code = "스포츠센터"; break;
            case "A03020200": code = "수련시설"; break;
            case "A03020300": code = "경기장"; break;
            case "A03020400": code = "인라인(실내인라인포함)"; break;
            case "A03020500": code = "자전거하이킹"; break;
            case "A03020600": code = "카트"; break;
            case "A03020700": code = "골프"; break;
            case "A03020800": code = "경마"; break;
            case "A03020900": code = "경륜"; break;
            case "A03021000": code = "카지노"; break;
            case "A03021100": code = "승마"; break;
            case "A03021200": code = "스키/스노보드"; break;
            case "A03021300": code = "스케이트"; break;
            case "A03021400": code = "썰매장"; break;
            case "A03021500": code = "수렵장"; break;
            case "A03021600": code = "사격장"; break;
            case "A03021700": code = "야영장,오토캠핑장"; break;
            case "A03021800": code = "암벽등반"; break;
            case "A03021900": code = "빙벽등반"; break;
            case "A03022000": code = "서바이벌게임"; break;
            case "A03022100": code = "ATV"; break;
            case "A03022200": code = "MTB"; break;
            case "A03022300": code = "오프로드"; break;
            case "A03022400": code = "번지점프"; break;
            case "A03022500": code = "자동차경주"; break;
            case "A03022600": code = "스키(보드)렌탈샵"; break;
            case "A03022700": code = "트래킹"; break;
            case "A03030100": code = "윈드서핑/제트스키"; break;
            case "A03030200": code = "카약/카누"; break;
            case "A03030300": code = "요트"; break;
            case "A03030400": code = "스노쿨링/스킨스쿠버다이빙"; break;
            case "A03030500": code = "민물낚시"; break;
            case "A03030600": code = "바다낚시"; break;
            case "A03030700": code = "수영"; break;
            case "A03030800": code = "래프팅"; break;
            case "A03040100": code = "스카이다이빙"; break;
            case "A03040200": code = "초경량비행"; break;
            case "A03040300": code = "헹글라이딩/패러글라이딩"; break;
            case "A03040400": code = "열기구"; break;
            case "A03050100": code = "복합레포츠"; break;//레포츠 끝

            case "B02010100": code = "관광호텔"; break;//숙박 시작
            case "B02010200": code = "수상관광호텔"; break;
            case "B02010300": code = "전통호텔"; break;
            case "B02010400": code = "가족호텔"; break;
            case "B02010500": code = "콘도미니엄"; break;
            case "B02010600": code = "유스호스텔"; break;
            case "B02010700": code = "펜션"; break;
            case "B02010800": code = "여관"; break;
            case "B02010900": code = "모텔"; break;
            case "B02011000": code = "민박"; break;
            case "B02011100": code = "게스트하우스"; break;
            case "B02011200": code = "홈스테이"; break;
            case "B02011300": code = "서비스드레지던스"; break;
            case "B02011400": code = "의료관광호텔"; break;
            case "B02011500": code = "소형호텔"; break;
            case "B02011600": code = "한옥스테이"; break;//숙박 끝

            case "A04010100": code = "5일장"; break;//쇼핑 시작
            case "A04010200": code = "상설시장"; break;
            case "A04010300": code = "백화점"; break;
            case "A04010400": code = "면세점"; break;
            case "A04010500": code = "할인매장"; break;
            case "A04010600": code = "전문상가"; break;
            case "A04010700": code = "공예,공방"; break;
            case "A04010800": code = "관광기념품점"; break;
            case "A04010900": code = "특산물판매점"; break; //쇼핑끝

            case "A05020100": code = "한식"; break;//음식 시작
            case "A05020200": code = "서양식"; break;
            case "A05020300": code = "일식"; break;
            case "A05020400": code = "중식"; break;
            case "A05020500": code = "아시아식"; break;
            case "A05020600": code = "패밀리레스토랑"; break;
            case "A05020700": code = "이색음식점"; break;
            case "A05020800": code = "채식전문점"; break;
            case "A05020900": code = "바/카페"; break;
            case "A05021000": code = "클럽"; break;//음식 끝

        } return code;
    }



}
