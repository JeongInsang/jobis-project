import { localAxios } from "@/apis/utils/axios";

const local = localAxios();

const url = "/news";


// 뉴스
function getNews(payload, success, fail) {
    local.get(`${url}/${payload.companyId}/${payload.name}`)
    .then(success)
    .catch(fail)
}

export {
    getNews,
}