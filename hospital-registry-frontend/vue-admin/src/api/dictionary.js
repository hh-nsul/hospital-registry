import request from '@/utils/request'

export default {

    getDictionaryList(id) {
        return request ({

            // `` indicates that it's a template string
            url: `/admin/dmn/dictionary/child-data/${id}`,
            method: 'get'
        })
    }
}