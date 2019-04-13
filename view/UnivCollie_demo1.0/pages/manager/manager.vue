<template>
	<view class="box">
		<view class="top" @touchstart="logout()">⬅退出登陆</view>
		<view class="title">当前求助信息--下拉刷新</view>
		<view v-for="(item, index) in students">
			<view class="list">{{item.stuname}}-{{item.sostype}}-时间:{{item.beigintime}}
				<view class="list1" @touchstart="getTel(item.stutel)">拨打电话</view>
				<view class="list1" @touchstart="openLocation(item.longitude,item.latitude)">查看位置</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data: {
			students: [],
			table: '<view class="list">某某某-医疗-电话：18313023631<view class="list1"  @longtap="getTel()">拨打电话</view><view class="list1">查看位置</view></view>'
		},
		onLoad() {
			uni.request({
				url: 'http://148.70.148.17:8080/sos/sosinfor',
				// url: 'http://192.168.191.1:8080/user/infor',
				success: (res) => {
					this.students = res.data;
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
			getTel(tel) {
				uni.makePhoneCall({
					phoneNumber: tel
				});
			},
			openLocation(longitude, latitude) {
				var longitude1 = longitude;
				var latitude1 = latitude;
				uni.openLocation({
					latitude: latitude1,
					longitude: longitude1,
					success: function() {
						console.log('success');
					},
					fail(res) {
						uni.showToast({
							icon: 'none',
							title: '本地地图调用失败！'
						});
					}
				});
			}
		},
		onPullDownRefresh() {
			uni.request({
				url: 'http://148.70.148.17:8080/sos/sosinfor',
				// url: 'http://192.168.191.1:8080/user/infor',
				success: (res) => {
					this.students = res.data;
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
		}
	}
</script>

<style>
	.box {
		height: 100%;
		width: 100%;

	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.top {
		font-size: 40upx;
		margin-top: 18%;
		height: 70upx;
		width: 30%;
		margin-right: 300upx;
		background-color: #F76260;
		border-radius: 0 50upx 50upx 0;
		color: #FFFFFF;
	}

	.title {
		font-size: 40upx;
		margin-top: 20upx;
		height: 70upx;
		width: 60%;
		background-color: #5bacf6;
		border-radius: 0 50upx 50upx 0;
		color: #333333;
	}

	.list {
		font-size: 40upx;
		margin-top: 1%;
		height: 170upx;
		width: 98%;
		margin-left: 1%;
		background-color: #92b0dd;
		/* border-top: #333333 solid 1upx;	 */
		/* border-bottom: #333333 solid 1upx; */
		border-radius: 10upx;
		/* color: #FFFFFF; */
		float: left;
	}

	.list1 {
		font-size: 40upx;
		margin-top: 2%;
		height: 70upx;
		width: 30%;
		background-color: #F1F1F3;
		border-radius: 20upx 20upx 20upx 20upx;
		color: #333333;
		margin-left: 10%;
		text-align: center;
		float: left;
	}

	.esc {
		width: 70%;
		margin-left: 15%;
		margin-top: 50upx;
		background-color: #F76260;
	}
</style>
