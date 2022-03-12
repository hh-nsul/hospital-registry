<template>
    <div class="app-container">
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
    </div>
</template>

<script>
import dictionary from '@/api/dictionary'

export default {
    
    data() {
        return {
            list: []
        }
    },

    created() {
        // 1 represents for the record of table hospital_data_management
        // where id = 1, parent_id = 0 and dict_node = ROOT 
        this.getDictList(1)
    },

    methods: {

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