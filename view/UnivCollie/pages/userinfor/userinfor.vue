<template>
	<div class="vbox">
		<image class="top_back_img" src="../../static/dulin-setting/set-top-bg.png" mode="aspectFit"></image>
		<view class="top">
			<view class="circle">
				<image class="head" src="../../static/dulin-setting/testuser.jpg" mode="widthFix"></image>
			</view>
			<view class="top-texts">
				<text class="name">{{username}}</text>
				<image class="set-top-hr" src="../../static/dulin-setting/set-top-hr.png" mode=""></image>
				<text class="depart">{{studentid}}</text>
				<view>
					<text>绑定手机：</text>
					<text>{{telnumber}}</text>
				</view>
			</view>
			<view class="top-changeInfo">
				<text>完善资料</text>
			</view>
		</view>

		<!-- 中间部分 -->
		<view class="middle">
			<view class="middle-left">
				<image class="middle-icon" src="../../static/dulin-setting/cust.png"></image>
				<text>打卡统计：</text>
				<text style="color:#12f358">{{pun_s}}</text>
				<text class="middle-num">/</text>
				<text style="color:#f31f1a">{{pun_f}}</text>
			</view>
			<view class="middle-line"></view>
			<view class="middle-right">
				<image class="middle-icon" src="../../static/dulin-setting/loan.png"></image>
				<text>安全积分：</text>
				<text class="middle-num">{{seq_s}}</text>
			</view>
		</view>

		<!-- 下半部分 -->
		<view class="index">
			<view id="c1" class="cell" @click="changeGray;realContent($event)">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link.png"></image>
					<text class="cell-text">辅导员</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>

			<view class="cell1" v-if="showPrise1">
				<image class="cell_icon" src="../../static/dulin-setting/link.png"></image>
				<text class="content">辅导员:{{teacher_name}}</text></br>
				<image class="cell_icon" src="../../static/dulin-setting/link.png"></image>
				<text class="content"> 联系号码:{{teacher_tel}}</text>
			</view>

			<view id="c2" class="cell" @click="changeGray;realContent($event)">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link1.png"></image>
					<text class="cell-text">安全员</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>
			<view class="cell2" v-if="showPrise2">
				<image class="cell_icon" src="../../static/dulin-setting/link1.png"></image>
				<text class="content">安全员:{{fre_name}} </text></br>
				<image class="cell_icon" src="../../static/dulin-setting/link1.png"></image>
				<text class="content">联系号码:{{fre_tel}}</text>
			</view>

			<view id="c3" class="cell" @click="changeGray;realContent($event)">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link2.png"></image>
					<text class="cell-text">安保部</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>
			<view class="cell3" v-if="showPrise3">
				<image class="cell_icon" src="../../static/dulin-setting/link2.png"></image>
				<text class="content">联系号码:{{pol_tel}}</text>
			</view>

			<view id="c4" class="cell" @click="changeGray;realContent($event)">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link3.png"></image>
					<text class="cell-text">校医院</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>
			<view class="cell4" v-if="showPrise4">
				<image class="cell_icon" src="../../static/dulin-setting/link3.png"></image>
				<text class="content">联系号码:{{doc_tel}}</text>
			</view>


			<view id="c5" class="cell" @click="changeGray;realContent($event)">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link4.png"></image>
					<text class="cell-text">关于</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>
			<view class="cell5" v-if="showPrise5">
				<text class="content">简介</text>
			</view>

			<view class="cell" @click="changeGray;logout()">
				<view class="cell-left">
					<image class="cell_icon" src="../../static/dulin-setting/link.png"></image>
					<text class="cell-text">退出登录</text>
				</view>
				<view class="cell-right">
					<image class="right-arrow" src="../../static/dulin-setting/right-arrow.png"></image>
				</view>
			</view>

		</view>

	</div>
</template>

<script>
	export default {
		data: {
			username: '请刷新',
			studentid: '--',
			telnumber: '--',
			pun_s: '--',
			pun_f: '--',
			seq_s: '--', //安全积分
			teacher_name: '--',
			teacher_tel: '--',
			fre_name: '--',
			fre_tel: '--',
			pol_tel: '--',
			doc_tel: '--',
			showPrise1: false,
			showPrise2: false,
			showPrise3: false,
			showPrise4: false,
			showPrise5: false
		},
		onLoad: function() {
			this.username = uni.getStorageSync("username");
			console.log(this.username);
			this.studentid = uni.getStorageSync("studentid");
			this.telnumber = uni.getStorageSync("telnumber");
			this.id = uni.getStorageSync("ID");
			uni.request({
				url: 'http://127.0.0.1:8080/user/infor',
				// url: 'http://192.168.191.1:8080/user/infor',
				data: {
					id: uni.getStorageSync("ID")
				},
				success: (res) => {
					this.pun_s = res.data.success;
					this.pun_f = res.data.fail;
					this.seq_s = res.data.safetyindex;
					this.teacher_name = res.data.teachername;
					this.teacher_tel = res.data.teachertel;
					this.fre_name = res.data.secname;
					this.fre_tel = res.data.sectel;
					this.pol_tel = res.data.securitytel;
					this.doc_tel = res.data.matterstel;
					uni.setStorageSync('allergylog', res.data.allergylog);
					uni.setStorageSync('medicationlog', res.data.medicationlog);
					uni.setStorageSync('illog', res.data.illog);
					uni.setStorageSync('begintime', res.data.begintime);
					uni.setStorageSync('overtime', res.data.overtime);
				},
				fail: (res) => {
					uni.showToast({
						icon: 'none',
						title: '网络服务异常！'
					});
				}
			})
		},
		onPullDownRefresh() {
			console.log('refresh');
			uni.request({
				url: 'http://127.0.0.1:8080/user/infor',
				// url: 'http://192.168.191.1:8080/user/infor',
				data: {
					id: uni.getStorageSync("ID")
				},
				success: (res) => {
					this.pun_s = res.data.success;
					this.pun_f = res.data.fail;
					this.seq_s = res.data.safetyindex;
					this.teacher_name = res.data.teachername;
					this.teacher_tel = res.data.teachertel;
					this.fre_name = res.data.secname;
					this.fre_tel = res.data.sectel;
					this.pol_tel = res.data.securitytel;
					this.doc_tel = res.data.matterstel;
					uni.setStorageSync('allergylog', res.data.allergylog);
					uni.setStorageSync('medicationlog', res.data.medicationlog);
					uni.setStorageSync('illog', res.data.illog);
					uni.setStorageSync('begintime', res.data.begintime);
					uni.setStorageSync('overtime', res.data.overtime);
					uni.stopPullDownRefresh();
				},
				fail: (res) => {
					uni.showToast({
						icon: 'none',
						title: '网络服务异常！'
					});
					uni.stopPullDownRefresh();
				}
			});
		},
		methods: {
			logout() {
				uni.showModal({
					title: '提示',
					content: '确认退出登陆？',
					success: function(res) {
						if (res.confirm) {
							uni.clearStorageSync();
							uni.reLaunch({
								url: '../login/login'
							});
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			realContent(e) {
				// console.log(e.currentTarget);
				switch (e.currentTarget.id) {
					case 'c1':
						this.showPrise1 = !this.showPrise1;
						break;
					case 'c2':
						this.showPrise2 = !this.showPrise2;
						break;
					case 'c3':
						this.showPrise3 = !this.showPrise3;
						break;
					case 'c4':
						this.showPrise4 = !this.showPrise4;
						break;
					case 'c5':
						this.showPrise5 = !this.showPrise5;
						break;
				}
			}


		}
	}
</script>

<style scoped>
	.index {
		display: flex;
		flex-direction: column;
		width: 100%;
		background-color: white;
		/* border-top: 1px solid #cccccc; */
	}

	.vbox {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.top_back_img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 420upx;

	}

	.top {
		display: flex;
		width: 100%;
		height: 420upx;
		align-items: center;
	}

	.circle {
		margin-left: 100upx;
		width: 120upx;
		height: 120upx;
		border: 6upx solid #F1F2F3;
		border-radius: 150upx;
		overflow: hidden;
	}

	.head {
		width: 120upx;
		height: 120upx;
		border-radius: 150upx;
	}

	.top-texts {
		display: flex;
		flex-direction: column;
		margin-left: 15upx;
		color: white;
	}

	.name {
		font-size: 36upx;
		font-weight: 500;
	}

	.set-top-hr {
		width: 210upx;
		height: 6upx;
	}

	.top-changeInfo {
		margin-top: 250upx;
		width: 120upx;
		height: 28upx;
		line-height: 28upx;
		background-color: #FFFFFF;
		border-radius: 15upx;
		padding: 10upx;
		color: #33dce8;
	}

	.middle {
		display: flex;
		align-items: center;
		position: relative;
		top: -50upx;
		width: 80%;
		height: 100upx;
		background-color: white;
		border-radius: 15upx;
	}

	.middle-line {
		width: 2upx;
		height: 80upx;
		background-color: #eeeeee;
	}

	.middle-left {
		flex-grow: 1;
		color: #666666;
		display: flex;
		align-items: center;
		text-align: center;
	}

	.middle-right {
		flex-grow: 1;
		color: #666666;
		display: flex;
		align-items: center;
		text-align: center;
	}

	.middle-icon {
		width: 40upx;
		height: 40upx;
		margin-left: 20upx;
	}

	.middle-num {
		color: #fcac45;
		-webkit-font-smoothing: antialiased;
	}

	.cell1,
	.cell2,
	.cell3,
	.cell4,
	.cell5 {
		width: 74%;
		margin-left: 15%;
	}

	.cell {
		display: flex;
		align-items: center;
		/* border-bottom: 1px solid #ccc; */
		height: 80upx;
		align-items: center;
		justify-content: space-between;
		margin-top: 0upx;
	}

	.cell:active {
		background-color: #def5f2;
		color: #66dad9;
		box-shadow: 1upx 1upx 25upx #ccc;
		border-radius: 5px;
	}

	.cell-left {
		display: flex;
		align-items: center;
		margin-left: 40upx;
	}

	.cell-text {
		margin-left: 25upx;
	}

	.cell-right {
		margin-right: 45upx;
		height: 28upx;
	}

	.cell_icon {
		width: 20upx;
		height: 20upx;
	}

	.right-arrow {
		color: #aaa;
		width: 15upx;
		height: 28upx;
	}

	.content {
		border-bottom: 1px solid #ccc;
	}
</style>
