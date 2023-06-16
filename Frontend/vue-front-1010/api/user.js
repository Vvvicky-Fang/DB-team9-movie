import request from '@/utils/request'

export default{
    getAllSupplierName() {
      return request({
        url: '/adminservice/user/getAllSupplierName',
        method: 'get',
      })
    },
    //get List of orders with conditons
    getSupllierListPage(current,limit,username){
      return request({
          url: `/adminservice/user/pageSupplierCondition/${current}/${limit}`,
          method: 'post',
          //data表示把对象转换成json，发送到接口
          params:{
            username
          }
        })  
    },
    addSupplier(supplier){
      return request({
          url: `/adminservice/user/createSupplier`,
          method: 'post',
          //data表示把对象转换成json，发送到接口
          data:supplier
        })  
    },
    updateSupplier(supplier){
      return request({
          url: `/adminservice/user/updateSupplier`,
          method: 'post',
          //data表示把对象转换成json，发送到接口
          data:supplier
        })  
    },
    getSupplierInfo(userID){
      return request({
          url: `/adminservice/user/getSupplierInfo/${userID}`,
          method: 'get'
        })  
    },
}

