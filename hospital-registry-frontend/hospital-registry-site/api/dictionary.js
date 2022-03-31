import request from '@/utils/request'

const api_name = `/admin/dmn/dictionary`

export default {

    getChildData(parentId) {
        return request({
            url: `${api_name}/child-data/${parentId}`,
            method: 'get'
        })
    },

    getSubNodesByDictCode(dictCode) {
        return request({
            url: `${api_name}/sub-nodes/${dictCode}`,
            method: 'get'
        })
    }
}