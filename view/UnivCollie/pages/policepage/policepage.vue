<template>
	<view class="box">
		<view class="broadcast">长按3秒以发出求助信息！</view>
		<image class="background-img" src="../../static/mediclpage/bg1.jpg"></image>
		<map id="polmap" class="map1" :latitude="latitude" :longitude="longitude" :markers="covers"></map>

		<image class="button1" src="../../static/policpage/button1.png"></image>
		<image class="button2" src="../../static/policpage/button2.png"></image>
	</view>
</template>

<script>
	export default {
		data: {
			locationtype: 'gcj02',
			longitude: 102.847126,
			latitude: 24.836836,
			covers: [{
				latitude: 24.836836,
				longitude: 102.847126,
				iconPath: '../../static/location@3x.png'
			}]
		},
		onLoad() {
			uni.getLocation({
				type: this.locationtype,
				success: function(res) {
					// 				uni.showToast({
					// 					title: '获取位置成功！',
					// 					duration: 2000
					// 				});
					this.longitude = res.longitude;
					this.latitude = res.latitude;
					console.log('当前位置的经度：' + res.longitude);
					console.log('当前位置的纬度：' + res.latitude);
				}
			});
			var mapContext = uni.createMapContext('polmap', this);
		}
	}
</script>

<style>
	.box {
		left: 0px;
		top: 0px;
		width: 100%;
		height: 100%;
	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.broadcast {
		height: 90upx;
		background: #00CED1;
		width: 98%;
		margin-left: 1%;
		margin-top: 18%;
		font-size: 24px;
		color: #F7F7F7;
		animation: 10s wordsLoop linear infinite normal;
	}

	.map1 {
		float: left;
		width: 90%;
		height: 700upx;
		margin-left: 5%;
		margin-top: 5%;
	}

	.button1,
	.button2 {
		width: 40%;
		height: 100upx;
		margin-top: 10%;
		margin-left: 6%;
		float: left;
	}

	.button1:active {
		width: 36%;
		height: 85upx;
		margin-top: 11%;
		margin-left: 8%;
		margin-right: 2%;
	}

	.button2:active {
		width: 36%;
		height: 85upx;
		margin-top: 11%;
		margin-left: 8%;

	}
</style>
