import request from '@/utils/request'

export default{
    getVideoAuth(id){
        return request({
            url:`/eduvod/video/getPlayAuth/${id}`,
            method:'get'
        })
    },
}