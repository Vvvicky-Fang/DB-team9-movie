import request from '@/utils/request'

export default{
    getMovieList(page, limit,searchObj){
        return request({
            url:`/movieservice/movie/getFrontMovieList/${page}/${limit}`,
            method:'post',
            data:searchObj
        })
    },
    getAllCate(){
        return request({
            url:`/movieservice/movie/getAllCate`,
            method:'get'
        })
    },
    getCourseInfo(id){
        return request({
            url:`/eduservice/coursefront/getFrontCourseInfo/${id}`,
            method:'get'
        })
    },
    getMovieInfo(id){
        return request({
            url:`/movieservice/movie/getMovieInfo/${id}`,
            method:'get'
        })
    },
    getNewMovies(num){
        return request({
            url:`/movieservice/movie/getNewMovies/${num}`,
            method:'get'
        })
    },
    getReviewByMovieId(movieId){
        return request({
            url:`/movieservice/review/GetReviewByMovieId/${movieId}`,
            method:'get'
        })
    },
    sendReview(reviewBody){
        return request({
            url:`/movieservice/review/addReview`,
            method:'post',
            data:reviewBody
        })
    },
    addCollect(userMovieFavorite){
        return request({
            url:`/movieservice/user-movie-favorite/addCollect`,
            method:'post',
            data:userMovieFavorite
        })
    },
    deleteCollect(userMovieFavorite){
        return request({
            url:`/movieservice/user-movie-favorite/cancelCollect`,
            method:'delete',
            data:userMovieFavorite
        })
    },
    isCollected(uid,mid){
        return request({
            url:`/movieservice/user-movie-favorite/isCollect/${uid}/${mid}`,
            method:'get'
        })
    },
    getMyCollections(uid){
        return request({
            url:`/movieservice/movie/getMyCollections/${uid}`,
            method:'get'
        })
    },
    getByMovieName(name){
        return request({
            url:`/movieservice/movie/getByMovieName/${name}`,
            method:'get'
        })
    },


}