import request from '@/utils/request'

export default{
    submitLogin(userInfo){
        return request({
            url:`/movieservice/user/login`,
            method:'post',
            data:userInfo
        })
    },

    //根据token获取用户信息
    getUserInfo(){
        return request({
            url:`/movieservice/user/getMemberInfo`,
            method:'get'
        })
    }
}