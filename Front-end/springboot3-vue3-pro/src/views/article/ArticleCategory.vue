<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ElMessage, formatter } from 'element-plus'
import { ref } from 'vue'
const categorys = ref([
    {
        "id": 3,
        "categoryName": "美食",
        "categoryAlias": "my",
        "createTime": "2023-09-02 12:06:59",
        "updateTime": "2023-09-02 12:06:59"
    },
])

import { articleCategoryListService } from '@/api/article.js'
const articleCategoryList = async () => {
   let result =  await articleCategoryListService()
   categorys.value =result.data
}
articleCategoryList()



//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const categoryModel = ref({
    categoryName: '',
    categoryAlias: ''
})
//添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}

import { articleCategoryAddService } from '@/api/article.js'
//调用接口添加表单
const addCategory = async () => {
    let result = await articleCategoryAddService(categoryModel.value)
    ElMessage.success(result.msg ? result.msg : '添加成功!')
    articleCategoryList()
    dialogVisible.value = false
}

let dialogVisibleTitle = ref('')

const showDialog = (row) => {
    dialogVisible.value = true;
    dialogVisibleTitle.value = '编辑分类'

    categoryModel.value.categoryName = row.categoryName
    categoryModel.value.categoryAlias = row.categoryAlias
    categoryModel.value.id = row.id

}


import { articleCategoryUpdateService } from '@/api/article.js'
const updateCategory = async () => {
    let result = await articleCategoryUpdateService(categoryModel.value)

    ElMessage.success(result.msg ? result.msg:"修改信息成功!")

    articleCategoryList()

    dialogVisible.value = false
}

const clearCategoryModelData = () => {
    categoryModel.value.categoryName = ''
    categoryModel.value.categoryAlias = ''
}

import { articleCategoryDeleteService } from '@/api/article.js'
import { ElMessageBox } from 'element-plus'
const deleteCategory = (row) => {
    ElMessageBox.confirm(
        '你确认删除该分类信息吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(async () => {
            //用户点击了确认
            let result = await articleCategoryDeleteService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            articleCategoryList()

    }).catch(() => {
            //用户点击了取消
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary" @click="clearCategoryModelData();dialogVisible=true;dialogVisibleTitle='添加分类'">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" @click="showDialog(row)" circle plain type="primary" ></el-button>
                    <el-button :icon="Delete" @click="deleteCategory(row)" circle plain type="danger"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <el-dialog v-model="dialogVisible" :title="dialogVisibleTitle" width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="dialogVisibleTitle=='添加分类' ? addCategory() : updateCategory()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>

    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>