/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
<template>
    <div class="app-container">
        Config List

    <!-- inline indicates that these el-form-items are placed in the same row -->
    <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
            <!-- v-model is bidirectional binding, so searchObj of 
            hospitalConfigApi.getHospitalConfigList() method can aquire the values in real time-->
            <el-input v-model="searchObj.hosname" placeholder="Hospital Name"/>
        </el-form-item>
        <el-form-item>
            <el-input v-model="searchObj.hoscode" placeholder="Hospital Code"/>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList()">Search</el-button>
        <el-button type="default" @click="resetData()">Clear</el-button>
    </el-form>

    <div>
        <el-button type="danger" size="mini" @click="removeItemsBatch()">Batch Delete</el-button>
    </div>

    <el-table :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column type="index" width="50" label="No."/>
        <el-table-column prop="hosname" label="Hospital Name" />
        <el-table-column prop="hoscode" label="Hospital Code    " />
        <el-table-column prop="apiUrl" label="API URL" />
        <el-table-column prop="contactsName" label="Contacts Name" />
        <el-table-column prop="contactsPhone" label="Contacts Phone" />
        <el-table-column label="status" width="80" >
            <template slot-scope="scope">
                {{ scope.row.status === 1 ? 'AVAIL.' : 'N/A' }}
            </template>
        </el-table-column>

        <el-table-column label="Operation" width="280" align="center">
            <template slot-scope="scope">

                <router-link :to="'/hospitalConfig/edit/' + scope.row.id">
                    <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
                </router-link>

                <el-button v-if="scope.row.status == 1" type="danger" size="mini" 
                    icon="el-icon-lock" @click="lockHospitalConfig(scope.row.id, 0)">Lock</el-button>
                <el-button v-if="scope.row.status == 0" type="primary" size="mini" 
                    icon="el-icon-unlock" @click="lockHospitalConfig(scope.row.id, 1)">Unlock</el-button>
                
                <el-button type="danger" size="mini" 
                    icon="el-icon-delete" @click="removeItemById(scope.row.id)">Delete</el-button>
                
            </template>
        </el-table-column>
    </el-table>

    <el-pagination
    :current-page="currPageNum"
    :page-size="recordsNum"
    :total="total"
    style="padding: 30px 0; text-align: center;"
    layout="total, prev, pager, next, jumper"
    @current-change="getList"
    />

    </div>
</template>

<script>

// Import the .js file where the interface defined
import hospitalConfigApi from '@/api/hospital-config'

export default {

    // Variables and initializing values are defined here
    data() {
        return {
            currPageNum: 1,
            recordsNum: 3,
            searchObj: {}, // Encapsulated object
            list: [], // Data collection of each page
            total: 0,
            multiSelection: []
        }
    },

    created() {
        this.getList()
    }, 

    methods: {

        getList(page = 1) {
            this.currPageNum = page
            // searchObj is aquired in <el-form-item>, due to v-model is bidirectional binding
            hospitalConfigApi.getHospitalConfigList(this.currPageNum, this.recordsNum, this.searchObj)
              .then((response) => {
                  this.list = response.data.records
                  this.total = response.data.total
                console.log(response)
            }).catch((err) => {
                console.log(err)
            })
        },

        removeItemById(id) {

            this.$confirm('This will permanently delete the hospital config info. Continue?', 'Warning', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {

                hospitalConfigApi.deleteHospitalConfig(id)
                    .then(response => {

                        this.$message({
                            type: 'success',
                            message: 'Delete completed'
                        });

                        // Refresh page
                        this.getList(1)
                    })
                
                
            })
            // }).catch(() => {
            //     this.$message({
            //         type: 'info',
            //         message: 'Delete canceled'
            //     });          
            // });
        },

        handleSelectionChange(selection) {
            this.multiSelection = selection
        },

        removeItemsBatch() {

            this.$confirm('This will permanently delete the hospital config info. Continue?', 'Warning', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
            }).then(() => {

                var idList = []

                for (var i = 0; i < this.multiSelection.length; ++i) {
                    idList.push(this.multiSelection[i].id)
                }

                console.log("idList: " + idList)

                hospitalConfigApi.deleteHospitalConfigBatch(idList)
                    .then(response => {

                        this.$message({
                            type: 'success',
                            message: 'Delete completed'
                        });

                        // Refresh page
                        this.getList(1)
                    })
            })
        },

        lockHospitalConfig(id, status) {
            hospitalConfigApi.lockHospitalConfig(id, status)
                .then(response => {
                    // Refresh page
                    this.getList(1)
                })
        }
    }
}
</script>
