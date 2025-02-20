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
            }
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
    },
};