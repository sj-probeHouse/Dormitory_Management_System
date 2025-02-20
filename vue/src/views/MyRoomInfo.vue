<template>
  <div>
    <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的宿舍</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="margin: 15px; min-height: calc(100vh - 111px)">
      <div style="display: flex">
        <div style="margin-top: 55px">
          <div style="margin-left: 50px;margin-top: 20px">
            <!--      房间信息-->
            <el-descriptions :column="1" border style="width: 500px" title="房间信息">
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <user/>
                    </el-icon>
                    楼宇号
                  </div>
                </template>
                <span class="rightSpan">{{ this.room.dormBuildId }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <location/>
                    </el-icon>
                    房间号
                  </div>
                </template>
                <span class="rightSpan">{{ this.room.dormRoomId }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <tickets/>
                    </el-icon>
                    楼层
                  </div>
                </template>
                <span class="rightSpan">{{ this.room.floorNum }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <office-building/>
                    </el-icon>
                    可住人数
                  </div>
                </template>
                <span class="rightSpan">{{ this.room.maxCapacity }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <iphone/>
                    </el-icon>
                    已住人数
                  </div>
                </template>
                <span class="rightSpan">{{ this.room.currentCapacity }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <warning/>
                    </el-icon>
                    缴费情况
                  </div>
                </template>
                <span class="rightSpan">
                {{ pay && pay.amount ? pay.amount : '无需缴费' }}  <!-- 显示缴费金额，如果不存在则显示“无” -->
            </span>
              </el-descriptions-item>
            </el-descriptions>
            <!-- 将按钮移动到表格外面 -->
            <div style="margin-top: 20px; text-align: left">
              <el-button
                  type="primary"
                  size="small"
                  @click="confirmPayment"
                  v-if="pay && pay.amount && Number(pay.amount) > 0" > <!-- 确保标签闭合 -->
              立即缴费
              </el-button>
            </div>
          </div>
          <!--      床位信息-->
          <div style="margin-left: 50px;margin-top: 40px">
            <el-descriptions :column="1" border style="width: 500px" title="床位信息">
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <user/>
                    </el-icon>
                    一号床位
                  </div>
                </template>
                <el-tag
                    v-if="this.room.firstBed != null"
                    :type="this.name === this.room.firstBed ? 'primary':'info'"
                    disable-transitions
                >{{ this.room.firstBed }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <location/>
                    </el-icon>
                    二号床位
                  </div>
                </template>
                <el-tag
                    v-if="this.room.secondBed != null"
                    :type="this.name === this.room.secondBed ? 'primary':'info'"
                    disable-transitions
                >{{ this.room.secondBed }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <tickets/>
                    </el-icon>
                    三号床位
                  </div>
                </template>
                <el-tag
                    v-if="this.room.thirdBed != null"
                    :type="this.name === this.room.thirdBed ? 'primary':'info'"
                    disable-transitions
                >{{ this.room.thirdBed }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template #label>
                  <div>
                    <el-icon>
                      <office-building/>
                    </el-icon>
                    四号床位
                  </div>
                </template>
                <el-tag
                    v-if="this.room.fourthBed != null"
                    :type="this.name === this.room.fourthBed ? 'primary':'info'"
                    disable-transitions
                >{{ this.room.fourthBed }}
                </el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </div>
        </div>
        <div style="margin-left: 100px;margin-top: 85px">
          <img alt="" src="../../public/myRoom.png" style="width: 600px">
        </div>
      </div>
    </el-card>
  </div>
</template>
<script src="@/assets/js/MyRoomInfo.js"></script>
<style scoped>@import '../assets/css/MyRoomInfo.css';</style>