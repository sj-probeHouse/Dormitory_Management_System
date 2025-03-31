import request from "@/utils/request";

const {ElMessage} = require("element-plus");

export default {
    name: "MyRoomInfo",
    data() {
        return {
            name: "",
            form: {
                username: "",
            },
            room: {
                dormRoomId: "",
                dormBuildId: "",
                floorNum: "",
                maxCapacity: "",
                currentCapacity: "",
                firstBed: "",
                secondBed: "",
                thirdBed: "",
                fourthBed: "",
            },
            pay:{
                amount:"",
            },
            dialogVisible: false, // 控制弹框显示与否
        };
    },
    created() {
        this.init();
        this.getInfo();
    },
    methods: {
        init() {
            this.form = JSON.parse(sessionStorage.getItem("user"));
            this.name = this.form.username;
        },
        getInfo() {
            //获取床位信息
            request.get("/room/getMyRoom/" + this.name).then((res) => {
                if (res.code === "0") {
                    this.room = res.data;
                    console.log(this.room);
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
            //获取缴费金额
            request.get("/room/getMyPay/" + this.name).then((res) => {
                if (res.code === "0") {
                    this.pay.amount = res.data;
                    console.log(this.pay);
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
        },
        handlePayment() {
            // 点击立即缴费时，显示弹框
            this.dialogVisible = true;
        },
        confirmPayment() {
            // 确认缴费，调用支付接口
            request.post("/room/confirmPayment/" + this.name).then((res) => {
                if (res.code === "0") {
                    ElMessage({
                        message: "缴费成功！",
                        type: "success",
                    });
                    this.dialogVisible = false; // 关闭弹框
                    this.getInfo(); // 刷新缴费信息
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
        },
    },
};