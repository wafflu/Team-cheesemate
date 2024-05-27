function remaindTime(endDate) {

    // 현재 시간을 구한다.
    let now = new Date();
    // 마감 기간을 가져온다.
    let end = new Date(endDate);

    // 현재 시간을 ms로 반환한다.
    let nt = now.getTime();
    // 마감 기간을 ms로 반환한다.
    let et = end.getTime();

    // 마감 기간이 현재 시간보다 클 경우
    if (nt > et) {
        sec = parseInt(et - nt) / 1000;
        days = parseInt(sec / 60 / 60 / 24) * -1;
        sec = sec - days * 60 * 60 * 24;
        hour = parseInt(sec / 60 / 60);
        sec = sec - hour * 60 * 60;
        min = parseInt(sec / 60);
        sec = parseInt(sec - min * 60);


        if (hour < 10) {
            if(hour < 0) {
                hour = hour * -1;
            }
        }
        if (min < 10) {
            if(min < 0) {
                min = min * -1;
            }

        }
        if (sec < 10) {
            if(sec < 0){
                sec = sec * -1;
            }

        }

        if(days > 0){
            return days+"일전";
        } else if(hour > 0){
            return hour+"시간전";
        } else if(min > 0) {
            return min+"분전";
        } else {
            return sec+"초전";
        }
    } else {
        return '종료';
    }
}

function comma(num){
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

function textlengover(txt, num) {
    let len = 20;
    if(num !== undefined){
        len = num;
    }
    let lastTxt = "...";
    if (txt.length > len) {
        txt = txt.substr(0, len) + lastTxt;
    }
    return txt;
}