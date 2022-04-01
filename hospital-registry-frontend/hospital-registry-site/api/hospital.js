import request from '@/utils/request'

const api_name = `/api/hospital/user`

export default {

    getHospitalList(page, limit, searchObj) {
        return request({
            url: `${api_name}/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    getHospitalByName(hosname) {
        return request({
            url: `${api_name}/${hosname}`,
            method: 'get'
        })
    },

    getHospitalDetailByHoscode(hoscode) {
        return request({
            url: `${api_name}/hospital/detail/${hoscode}`,
            method: 'get'
        })
    },

    getDepartmentByHoscode(hoscode) {
        return request({
            url: `${api_name}/department/${hoscode}`,
            method: 'get'
        })
    }

}