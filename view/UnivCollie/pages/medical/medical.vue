<template>
	<view class='box'>

		<view class="top">


		</view>

		<view class="content">
			<view class="button-help">
				<image class="button-help-img" src="../../static/mediclpage/button3.png"></image>
			</view>
		</view>

		<view class="bottom">
			<view class="button-allergy">
				<image class="button-allergy-img" src="../../static/mediclpage/allergylog.png"></image>
			</view>
			<view class="button-medication">
				<image class="button-medication-img" src="../../static/mediclpage/medicationlog.png"></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style scoped>
	
	.box {
		width: 100%;
		align-items: center;
	}

	.top {
		width: 98%;
		height: 220upx;
		border: 3upx solid #333333;
		margin-left: auto;
		margin-right: auto;
	}
	
	.content {
		width: 98%;
		height: 550upx;
		border: 3upx solid #333333;
		margin-left: auto;
		margin-right: auto;
	}
	
	.bottom{
		width: 98%;
		height: 250upx;
		border: 3upx solid #333333;
	}
	
	.button-help{
		width: 70%;
		height: 550upx;
		margin-left: auto;
		margin-right: auto;
	}
	.button-help-img {
		width: 100%;
		height: 100%;
	}

	.button-allergy-img,
	.button-medication-img {
		float: left;
		width: 160upx;
		height: 160upx;
	}
</style>
