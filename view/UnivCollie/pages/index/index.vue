<template>
	<view class="content">
        <map class="map1" :latitude="latitude" :longitude="longitude" :markers="covers"></map>
		<view>
            <text class="title">{{title}}</text>
			<button type="text" @tap="clean()">清除缓存数据</button>
			<button type="text" @tap="getLocation()">获取坐标位置</button>
        </view>
	</view>
</template>

<script>
	export default {
		data() {
			return{
				title:"text",
				locationtype:'gcj02',
				latitude: 24.836836 ,
				longitude: 102.847126,
				covers: [{
					latitude: 24.836836,
					longitude: 102.847126,
					iconPath: '../../static/location@3x.png'
				}]
			}	
		},
		methods: {
			clean:function(){
				uni.clearStorageSync();
				uni.showToast({
					title: '清除成功！',
					duration: 2000
				});
			},
			getLocation:function(){
				uni.getLocation({
					type: this.locationtype,
					success: function (res) {
					uni.showToast({
						title: '获取位置成功！',
						duration: 2000
					});
					console.log('当前位置的经度：' + res.longitude);
					console.log('当前位置的纬度：' + res.latitude);
					}
				});
			}
		}
	}

</script>

<style>	
	.content {
		text-align: center;
		height:700upx;
		width:100%
	}
    .map1{
        height: 100%;
        width: 100%;
        margin-top: 200upx;
    }
	.title {
		font-size: 36upx;
		color: #8f8f94;
	}
</style>
