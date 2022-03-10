import request from '@/utils/request'

export default {

    getHospitalConfigList(currPageNum, recordsNum, searchObj) {
        return request ({

            // `` indicates that it's a template string
            url: `/admin/hospital/hospitalConfig/findByPage/${currPageNum}/${recordsNum}`,
            method: 'post',
            data: searchObj // data keyword indicates that the object will be transferred in JSON format
        })
    },

    deleteHospitalConfig(id) {
        return request ({
            url: `/admin/hospital/hospitalConfig/${id}`,
            method: 'delete'
        })
    },

    deleteHospitalConfigBatch(idList) {
        return request ({
            url: `/admin/hospital/hospitalConfig/deleteBatch`,
            method: 'delete',
            data: idList  // Translate the list into JSON Format
        })
    },

    lockHospitalConfig(id, status) {
        return request ({
            url: `/admin/hospital/hospitalConfig/lockHospitalConfig/${id}/${status}`,
            method: 'put'
        })
    }
}
