<script>
	export default {
		onLaunch: function () {
			console.log('App Launch')
		},
		onShow: function () {
			console.log('App Show')
		},
		onHide: function () {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import  "./common/uni.css";/*导入uni.css*/
	
	    page {
	        min-height: 100%;
	        display: flex;
			justify-content: center;
	    }
	
</style>
