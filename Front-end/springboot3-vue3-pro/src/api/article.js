import request from '@/utils/request.js'
import { useTokenStore } from '../stores/token.js'

//文章分类查询
export const articleCategoryListService = () => {
    const tokenStore = useTokenStore()
    // return request.get('/category',{ headers:{'Authorization': tokenStore.token } })
    return request.get('/category')
}

//文章分类添加
export const articleCategoryAddService = (categoryData) => {
    return request.post('/category',categoryData)
}

//文章分类更新
export const articleCategoryUpdateService = (categoryData) => {
    return request.put('/category',categoryData)
}

//文章分类删除
export const articleCategoryDeleteService = (id) => {
    return request.delete('/category?id='+id)
}

//文章列表查询
export const articleListService = (params) => {
    return request.get('/article',{params:params})
}

//文章添加
export const articleAddService = (articleData) => {
    return request.post('/article',articleData)
}