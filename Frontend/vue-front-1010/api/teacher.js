import request from '@/utils/request'

export default{
    getActorInfo(actorId){
        return request({
            url:`/movieservice/actor/GetActorInfo/${actorId}`,
            method:'get'
        })
    },
    // getAllActorList(page,limit,name){
    //     return request({
    //         url:`/movieservice/actor/GetAllActors/${page}/${limit}`,
    //         method:'post',
    //         params: { name }
    //     })
    // },
    getAllActorList(page,limit){
        return request({
            url:`/movieservice/actor/GetAllActors/${page}/${limit}`,
            method:'post'
        })
    },
    getTopActor(num){
        return request({
            url:`/movieservice/actor/GetTopActors/${num}`,
            method:'get'
        })
    },
    getDirectorInfo(dId){
        return request({
            url:`/movieservice/director/GetDirectorInfo/${dId}`,
            method:'get'
        })
    },

}