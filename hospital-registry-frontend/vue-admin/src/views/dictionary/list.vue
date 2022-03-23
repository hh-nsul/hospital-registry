<template>
    <div class="app-container">
        <div class="el-toolbar">
            <div class="el-toolbar-body" style="justify-content: flex-start;">
                <!-- Open a new tab to download  -->
                <a href="http://localhost:8202/admin/dmn/dictionary/data-all-export" target="_blank">
                    <el-button type="text"><i class="fa fa-plus"/>Export</el-button>
                </a>
                <el-button type="text" @click="importDictionaryData"><i class="fa fa-plus"/>Import...</el-button>
                
            </div>
        </div>
        <el-table
        :data="list"
        style="width: 100%"
        row-key="id"
        border
        lazy
        :load="getChildren"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column label="Name" width="230" align="left">
            <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
            </template>
            </el-table-column>

            <el-table-column label="Code" width="220">
            <template slot-scope="{row}">
                    {{ row.dictCode }}
            </template>
            </el-table-column>
            <el-table-column label="Value" width="230" align="left">
            <template slot-scope="scope">
            <span>{{ scope.row.value }}</span>
            </template>
            </el-table-column>
            <el-table-column label="Creation Time" align="center">
            <template slot-scope="scope">
            <span>{{ scope.row.createTime }}</span>
            </template>
            </el-table-column>
        </el-table>

        <el-dialog title="Import" :visible.sync="dialogImportVisible" width="480px">
            <el-form label-position="right" label-width="170px">

                <el-form-item label="File">
                    <el-upload
                    :multiple="false"
                    :on-success="onUploadSuccess"
                    :action="'http://localhost:8202/admin/dmn/dictionary/data-all-import'"
                    class="upload-demo">
                    <el-button size="small" type="primary">Click to upload</el-button>
                    <div slot="tip" class="el-upload__tip">Only .xls file allowed, no bigger than 500kb</div>
                    </el-upload>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
        <el-button @click="dialogImportVisible = false">Cancel</el-button>
        </div>
        </el-dialog>
    </div>
</template>

<script>
import dictionary from '@/api/dictionary'

export default {
    
    data() {
        return {
            dialogImportVisible: false,
            list: []
        }
    },

    created() {
        // 1 represents for the record of table hospital_data_management
        // where id = 1, parent_id = 0 and dict_node = ROOT 
        this.getDictList(1)
    },

    methods: {

        importDictionaryData() {
            // Prompt a dialog
            this.dialogImportVisible = true
        },

        onUploadSuccess() {
            this.dialogImportVisible = false

            this.getDictList(1)
        },

        getDictList(id) {
            dictionary.getDictionaryList(id)
                .then(response => {
                    this.list = response.data
                })
        },

        exportDictionaryData() {
            window.location.href = "http://localhost:8202/admin/dmn/dictionary/data-all-export"
        },

        getDictList(id) {
            dictionary.getDictionaryList(id)
                .then(response => {
                    this.list = response.data
                })
        },

        getChildren(tree, treeNode, resolve) {
            dictionary.getDictionaryList(tree.id)
                .then(response => {
                    resolve(response.data)
                })
        }
    }
}
</script>