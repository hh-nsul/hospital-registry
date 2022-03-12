import request from '@/utils/request'

export default {

    getHospitalConfigList(currPageNum, recordsNum, searchObj) {
        return request ({

            // `` indicates that it's a template string
            url: `/admin/hospital/hospital-config/page/${currPageNum}/${recordsNum}`,
            method: 'post',
            data: searchObj // data keyword indicates that the object will be transferred in JSON format
        })
    },

    deleteHospitalConfig(id) {
        return request ({
            url: `/admin/hospital/hospital-config/${id}`,
            method: 'delete'
        })
    },

    deleteHospitalConfigBatch(idList) {
        return request ({
            url: `/admin/hospital/hospital-configs`,
            method: 'delete',
            data: idList  // Translate the list into JSON Format
        })
    },

    lockHospitalConfig(id, status) {
        return request ({
            url: `/admin/hospital/hospital-config/${id}/${status}`,
            method: 'put'
        })
    },

    saveHospitalConfig(hospitalConfig) {
        return request ({
            url: `/admin/hospital/hospital-config`,
            method: 'post',
            data: hospitalConfig
        })
    },

    updateHospitalConfig(hospitalConfig) {
        return request ({
            url: `/admin/hospital/hospital-config`,
            method: 'put',
            data: hospitalConfig
        })
    },

    getHospitalConfig(id) {
        return request ({
            url: `/admin/hospital/hospital-config/${id}`,
            method: 'get'
        })
    }
}
