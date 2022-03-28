<template>
<div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
    <el-form-item>
        <el-select
            v-model="searchObj.provinceCode"
            placeholder="Province"
                @change="provinceChanged">
            <el-option
                v-for="item in provinceList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"/>
        </el-select>
    </el-form-item>

    <el-form-item>
        <el-select
        v-model="searchObj.cityCode"
        placeholder="City"
        @change="cityChanged">
            <el-option
            v-for="item in cityList"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
        </el-select>
    </el-form-item>

    <el-form-item>
        <el-input v-model="searchObj.hosname" placeholder="Hospital Name"/>
    </el-form-item>

    <el-button type="primary" icon="el-icon-search" @click="fetchData()">Search</el-button></el-button>
    <el-button type="default" @click="resetData()">CLear</el-button>
    </el-form>

    <!-- banner列表 -->
    <el-table v-loading="listLoading" :data="list"
            border
        fit
        highlight-current-row>

        <el-table-column
        label="No."
        width="60"
        align="center">
            <template slot-scope="scope">
                    {{ (page - 1) * limit + scope.$index + 1 }}
            </template>
        </el-table-column>

        <el-table-column label="Logo">
            <template slot-scope="scope">
            <img :src="'data:image/jpeg;base64,'+scope.row.logoData" width="80">
            </template>
        </el-table-column>

        <el-table-column prop="hosname" label="Hostiptal Name"/>
        <el-table-column prop="param.hospitalTypeName" label="Type" width="90"/>
        <el-table-column prop="param.fullAddress" label="Address"/>
        <el-table-column label="Status" width="80">
            <template slot-scope="scope">
                    {{ scope.row.status === 0 ? 'online' : 'offline' }}
            </template>
        </el-table-column>
        <el-table-column prop="createTime" label="Creation Time"/>

        <el-table-column label="Operation" width="230" align="center">
            <template slot-scope="scope">

                <router-link :to = "'/hospitalConfig/hospital/detail/' + scope.row.id">
                    <el-button type="primary" size="mini">Detail</el-button>
                </router-link>

                <el-button v-if="scope.row.status == 1"  type="primary" size="mini" @click="updateStatus(scope.row.id, 0)">Offline</el-button>
                <el-button v-if="scope.row.status == 0"  type="danger" size="mini" @click="updateStatus(scope.row.id, 1)">Online</el-button>
            </template>
        </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
        :current-page="page"
        :total="total"
        :page-size="limit"
        :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
        style="padding: 30px 0; text-align: center;"
        layout="sizes, prev, pager, next, jumper, ->, total, slot"
        @current-change="fetchData"
        @size-change="changeSize"
    />
</div>
</template>

<script>
import hospitalApi from '@/api/hospital'
export default {
    data() {
        return {
            listLoading: true, // 数据是否正在加载
            list: null, // banner列表
            total: 0, // 数据库中的总记录数
            page: 1, // 默认页码
            limit: 10, // 每页记录数
            searchObj: {}, // 查询表单对象
            provinceList: [],
            cityList: [],
            districtList: []
        }
    },

    created() {
       
        this.fetchData()
        
        this.getAllProvince()
    },
    
    methods: {
        fetchData(page = 1) {
            this.page = page
            hospitalApi.getHospitalList(this.page, this.limit, this.searchObj)
                       .then(response => {
                            this.list = response.data.content
                            this.total = response.data.totalElements
                            this.listLoading = false
                        })
        },

        getAllProvince() {
            hospitalApi.getSubNodesByDictCode('Province')
                       .then(response => {
                           this.provinceList = response.data
                       })
        },

        provinceChanged() {
            this.cityList = []
            this.searchObj.cityCode = null
            this.districtList = []

            hospitalApi.getChildData(this.searchObj.provinceCode)
                       .then(response => {
                           this.cityList = response.data
                       })
        },

        changeSize(size) {
            this.limit = size
            this.fetchData(1)
        },

        updateStatus(id, status) {
            hospitalApi.updateStatus(id, status)
                       .then( response => {
                           this.fetchData()
                       })
        }
    }
    
}
</script>