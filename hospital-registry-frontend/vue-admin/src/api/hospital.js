import request from '@/utils/request'

export default {

    getHospitalList(page, limit, searchObj) {
        return request ({

            // `` indicates that it's a template string
            url: `/admin/hospital/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    getSubNodesByDictCode(dictCode) {
        return request ({
            url: `/admin/dmn/dictionary/sub-nodes/${dictCode}`,
            method: 'get' 
        })
    },

    getChildData(parentId) {
        return request ({
            url: `/admin/dmn/dictionary/child-data/${parentId}`,
            method: `get`
        })
    },

    updateStatus(id, status) {
        return request ({
            url: `/admin/hospital/${id}/${status}`,
            method: `get`
        })
    },

    getHospitalDetailById(id) {
        return request ({
            url: `/admin/hospital/detail/${id}`,
            method: `get`
        })
    }
}