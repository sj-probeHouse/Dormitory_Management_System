import request from "@/utils/request";

const {ElMessage} = require("element-plus");

export default {
    name: "PayInfo",
    components: {},
    data() {
        return {
            search: '',
            loading: false,
            tableData: [], //用于记录所有学生的缴费情况信息
            currentPage: 1,
            pageSize: 10,
            total: 0,
            dialogVisible: false,
            form: {
                amount: null,
                memoInfo: ''
            },
            rules: {
                amount: [{ required: true, message: '请输入缴费金额', trigger: 'blur' }],
                memoInfo: [{ required: true, message: '请输入备注', trigger: 'blur' }]
            }
        };
    },
    created() {
        this.load();
        this.loading = true;
        setTimeout(() => {
            //设置延迟执行
            this.loading = false;
        }, 1000);
    },
    methods: {
        async load() {
            // 加载缴费数据内容
            request.get("/pay/find",{
                params: {
                    pageNum: this.currentPage,
                    pageSize: this.pageSize,
                    search: this.search,
                },
            }).then((res) => {
                console.log(res);
                this.tableData = res.data.records; //TODO 更新所有学生的缴费情况信息
                this.total = res.data.total;
                this.loading = false;
            });
        },
        reset() {  //刷新页面
            request.get("/pay/find", {
                params: {
                    pageNum: 1,
                    pageSize: this.pageSize,
                    search: this.search,
                },
            }).then((res) => {
                console.log(res);
                this.tableData = res.data.records; //TODO 需要抓包看看res.data的结构
                this.total = res.data.total;
                this.loading = false;
            });
        },
        add() {
            this.dialogVisible = true;
        },
        cancel() {
            this.dialogVisible = false;
        },
        save() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    console.log(this.form)
                    request.post('/pay/savePayment', this.form)
                        .then(response => {
                            this.$message.success('缴费信息已保存');
                            this.dialogVisible = false;
                            this.load();  // 重新加载数据
                        })
                        .catch(error => {
                            this.$message.error('保存失败');
                        });
                } else {
                    console.log('表单验证失败');
                }
            });
        },
        handleSizeChange(size) {
            this.pageSize = size;
            this.load();
        },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.load();
        }
    }
};