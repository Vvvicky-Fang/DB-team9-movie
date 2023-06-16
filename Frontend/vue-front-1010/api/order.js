import request from '@/utils/request'

export default{
    createOrder(courseId){
        return request({
            url:`/eduorder/order/createOrder/`+courseId,
            method:'post'
        })
    },

    searchOrderById(id){
        return request({
            url:`/eduorder/order/getOrderInfo/`+id,
            method:'get'
        })
    },
    //生成二維碼
    createNative(orderNo){
        return request({
            url:`/eduorder/pay-log/createNative/`+orderNo,
            method:'get'
        })
    },
    //
    queryOrderStatus(orderNo){
        return request({
            url:`/eduorder/pay-log/queryPayStatus/`+orderNo,
            method:'get'
        })
    },
}