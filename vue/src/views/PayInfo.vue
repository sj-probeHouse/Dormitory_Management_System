<template>
  <div>
    <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>宿舍管理</el-breadcrumb-item>
      <el-breadcrumb-item>缴费管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="margin: 15px; min-height: calc(100vh - 111px)">
      <div>
        <!-- 功能区 -->
        <div style="margin: 10px 0">
          <!-- 搜索区 -->
          <div style="margin: 10px 0">
            <el-input v-model="search" clearable placeholder="请输入学生姓名" prefix-icon="Search" style="width: 20%"/>
            <el-button icon="Search" style="margin-left: 5px" type="primary" @click="load"></el-button>
            <el-button icon="refresh-left" style="margin-left: 10px" type="default" @click="reset"></el-button>
            <div style="float: right">
              <el-tooltip content="添加" placement="top">
                <el-button icon="plus" style="width: 50px" type="primary" @click="add"></el-button>
              </el-tooltip>
            </div>
          </div>
        </div>
        <!-- 表格 -->
        <el-table v-loading="loading" :data="tableData" border max-height="705" show-overflow-tooltip style="width: 100%">
          <el-table-column label="#" type="index"/>
          <el-table-column label="学生姓名" prop="studentName"/>
          <el-table-column label="待缴金额" prop="amount">
            <template #default="scope">
              <!-- 通过 scope.row.amount 来访问当前行的 amount 值 -->
              {{ scope.row.amount === null || scope.row.amount === 0 ? '0' : scope.row.amount }}
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="memo"/>
          <el-table-column label="缴费结果" prop="isPaid">
            <template #default="scope">
<!--              <div>{{ scope.row.isPaid }}</div>-->
              <el-tooltip
                  placement="top"
                  :content="scope.row.isPaid === 0 ? '已缴费' : '未缴费'"
              >

                <i
                    :class="scope.row.isPaid === 0 ? 'el-icon-check' : 'el-icon-close'"
                    :style="scope.row.isPaid === 0 ? 'color: green;' : 'color: red;'"
                >{{ scope.row.isPaid === 0 ? '已缴费' : '未缴费' }}</i>
              </el-tooltip>
            </template>
          </el-table-column>

        </el-table>
        <!-- 分页 -->
        <div style="margin: 10px 0">
          <el-pagination
              v-model:currentPage="currentPage"
              :page-size="pageSize"
              :page-sizes="[10, 20]"
              :total="total"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          >
          </el-pagination>
        </div>
        <!-- 弹窗 -->
        <el-dialog v-model="dialogVisible" title="发布缴费信息" width="30%" @close="cancel">
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="缴费金额" prop="amount">
              <el-input v-model.number="form.amount" style="width: 80%"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="memoInfo">
              <el-input v-model="form.memoInfo" :autosize="{ minRows: 2, maxRows: 4 }" autosize style="width: 80%" type="textarea"></el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="cancel">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </span>
          </template>
        </el-dialog>
      </div>
    </el-card>
  </div>
</template>
<script src="@/assets/js/PayInfo.js"></script>
