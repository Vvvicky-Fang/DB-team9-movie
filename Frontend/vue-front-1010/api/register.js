import request from '@/utils/request'

export default{
    // sendPhoneCode(PhoneNumber){
    //     return request({
    //         url:`/edumsm/msm/send/${PhoneNumber}`,
    //         method:'get'
    //     })
    // },

    registerUser(formItem){
        return request({
            url:`/movieservice/user/register`,
            method:'post',
            data:formItem
        })
    }
}