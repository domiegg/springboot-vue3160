<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','book_name') || $check_field('add','book_name') || $check_field('set','book_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="图书名称" prop="book_name">
					<el-input id="book_name" v-model="form['book_name']" placeholder="请输入图书名称"
							  v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','book_name')) || (!form['books_and_materials_id'] && $check_field('add','book_name'))" :disabled="disabledObj['book_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','book_name')">{{form['book_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','cover')) || (!form['books_and_materials_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','author') || $check_field('add','author') || $check_field('set','author')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="作者" prop="author">
					<el-input id="author" v-model="form['author']" placeholder="请输入作者"
							  v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','author')) || (!form['books_and_materials_id'] && $check_field('add','author'))" :disabled="disabledObj['author_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','author')">{{form['author']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','press') || $check_field('add','press') || $check_field('set','press')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="出版社" prop="press">
					<el-input id="press" v-model="form['press']" placeholder="请输入出版社"
							  v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','press')) || (!form['books_and_materials_id'] && $check_field('add','press'))" :disabled="disabledObj['press_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','press')">{{form['press']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publication_time') || $check_field('add','publication_time') || $check_field('set','publication_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="出版时间" prop="publication_time">
					<el-date-picker :disabled="disabledObj['publication_time_isDisabled']" v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','publication_time')) || (!form['books_and_materials_id'] && $check_field('add','publication_time'))" id="publication_time"
						v-model="form['publication_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','publication_time')">{{form['publication_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','content_validity') || $check_field('add','content_validity') || $check_field('set','content_validity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="内容简介" prop="content_validity">
					<el-input type="textarea" id="content_validity" v-model="form['content_validity']" placeholder="请输入内容简介"
						v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','content_validity')) || (!form['books_and_materials_id'] && $check_field('add','content_validity'))" :disabled="disabledObj['content_validity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','content_validity')">{{form['content_validity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['books_and_materials_id'] && $check_field('set','details')) || (!form['books_and_materials_id'] && $check_field('add','details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','details')" v-html="form['details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "books_and_materials_id",
				url_add: "~/api/books_and_materials/add?",
				url_set: "~/api/books_and_materials/set?",
				url_get_obj: "~/api/books_and_materials/get_obj?",
				url_upload: "~/api/books_and_materials/upload?",

				query: {
					"books_and_materials_id": 0,
				},

				form: {
					"book_name":'', // 图书名称
					"cover":'', // 封面
					"author":'', // 作者
					"press":'', // 出版社
					"publication_time":'', // 出版时间
					"content_validity":'', // 内容简介
					"details":'', // 详情
					"books_and_materials_id": 0, // ID

				},
				disabledObj:{
					"book_name_isDisabled": false,
					"cover_isDisabled": false,
					"author_isDisabled": false,
					"press_isDisabled": false,
					"publication_time_isDisabled": false,
					"content_validity_isDisabled": false,
					"details_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["publication_time"].indexOf("-")===-1){
          this.form["publication_time"] = this.$toTime(parseInt(this.form["publication_time"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["publication_time"]) > 9999){
					this.form["publication_time"] = this.$toTime(parseInt(this.form["publication_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/books_and_materials/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/books_and_materials/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/books_and_materials/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/books_and_materials/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/books_and_materials/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
