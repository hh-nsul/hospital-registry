<template>
    <div class="app-container">
        Config Add

        <el-form label-width="120px">
          <el-form-item label="Hospital Name">
              <el-input v-model="hospitalConfig.hosname"/>
          </el-form-item>
          <el-form-item label="Hospital Code">
              <el-input v-model="hospitalConfig.hoscode"/>
          </el-form-item>
          <el-form-item label="API URL Path">
              <el-input v-model="hospitalConfig.apiUrl"/>
          </el-form-item>
          <el-form-item label="Contact Name">
              <el-input v-model="hospitalConfig.contactsName"/>
          </el-form-item>
          <el-form-item label="Contact Phone">
              <el-input v-model="hospitalConfig.contactsPhone"/>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="saveOrUpdate">Save</el-button>
          </el-form-item>
        </el-form>

    </div>
</template>

<script>

// Import the .js file where the interface defined
import hospitalConfigApi from '@/api/hospital-config'

export default {
    data() {
        return {
            hospitalConfig: {}
        }
    },

    created() {

        if (this.$route.params && this.$route.params.id) {
            const id = this.$route.params.id
            this.getHosConfig(id)
        }
    }, 

    methods: {

        getHosConfig(id) {
            hospitalConfigApi.getHospitalConfig(id)
                .then(response => {
                    this.hospitalConfig = response.data
                })
        },

        update() {
            hospitalConfigApi.updateHospitalConfig(this.hospitalConfig)
                .then(response => {
                    this.$message({
                        type: 'success',
                        message: 'Successfully Updated'
                    })
                })

                // Refresh page
                this.$router.push({ path: '/hospitalConfig/list'})
        },

        save() {
            hospitalConfigApi.saveHospitalConfig(this.hospitalConfig)
                .then(response => {
                    this.$message({
                        type: 'success',
                        message: 'Successfully Added'
                    })
                })

                // Refresh page
                this.$router.push({ path: '/hospitalConfig/list'})
        },

        saveOrUpdate() {

            if (this.hospitalConfig.id) {
                this.update()
            } else {
                this.save()
            }
        }
    }
}
</script>