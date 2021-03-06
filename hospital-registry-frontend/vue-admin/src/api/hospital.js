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
    },

    getDepartmentByHoscode(hoscode) {
        return request ({
            url: `/admin/hospital/department/list/${hoscode}`,
            method: `get`
        })
    },

    getScheduleRule(page, limit, hoscode, depcode) {
        return request ({
            url: `/admin/hospital/schedule/rule/${page}/${limit}/${hoscode}/${depcode}`,
            method: `get`
        })
    },

    getScheduleDetail(hoscode, depcode, workDate) {
        return request ({
            url: `/admin/hospital/schedule/detail/${hoscode}/${depcode}/${workDate}`,
            method: `get`
        })
    }
}