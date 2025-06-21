<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="党章党规"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/development_history/list', 'get')"
				:list="result_development_history_title"
				title="发展历程标题"
				source_table="development_history"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/books_and_materials/list', 'get')"
				:list="result_books_and_materials_book_name"
				title="图书资料图书名称"
				source_table="books_and_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/books_and_materials/list', 'get')"
				:list="result_books_and_materials_author"
				title="图书资料作者"
				source_table="books_and_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/classic_literature/list', 'get')"
				:list="result_classic_literature_document_name"
				title="经典文献文献名称"
				source_table="classic_literature"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/classic_literature/list', 'get')"
				:list="result_classic_literature_publication_date"
				title="经典文献发表日期"
				source_table="classic_literature"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/video_data/list', 'get')"
				:list="result_video_data_video_name"
				title="视频资料视频名称"
				source_table="video_data"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/video_data/list', 'get')"
				:list="result_video_data_author"
				title="视频资料作者"
				source_table="video_data"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_registered_user_user_name":[],
			"result_development_history_title":[],
			"result_books_and_materials_book_name":[],
			"result_books_and_materials_author":[],
			"result_classic_literature_document_name":[],
			"result_classic_literature_publication_date":[],
			"result_video_data_video_name":[],
			"result_video_data_author":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_development_history_title(){
		this.$get("~/api/development_history/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_development_history_title = json.result.list;
			result_development_history_title.map(o => o.title = o['title'])
	  			this.result_development_history_title = result_development_history_title
		 	}
		});
	},
	/**
	 * 获取book_name
	 */
	get_books_and_materials_book_name(){
		this.$get("~/api/books_and_materials/get_list?like=0", { page: 1, size: 10, "book_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_books_and_materials_book_name = json.result.list;
			result_books_and_materials_book_name.map(o => o.title = o['book_name'])
	  			this.result_books_and_materials_book_name = result_books_and_materials_book_name
		 	}
		});
	},
	/**
	 * 获取author
	 */
	get_books_and_materials_author(){
		this.$get("~/api/books_and_materials/get_list?like=0", { page: 1, size: 10, "author": this.query.word }, (json) => {
		  if (json.result) {
			var result_books_and_materials_author = json.result.list;
			result_books_and_materials_author.map(o => o.title = o['author'])
	  			this.result_books_and_materials_author = result_books_and_materials_author
		 	}
		});
	},
	/**
	 * 获取document_name
	 */
	get_classic_literature_document_name(){
		this.$get("~/api/classic_literature/get_list?like=0", { page: 1, size: 10, "document_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_classic_literature_document_name = json.result.list;
			result_classic_literature_document_name.map(o => o.title = o['document_name'])
	  			this.result_classic_literature_document_name = result_classic_literature_document_name
		 	}
		});
	},
	/**
	 * 获取publication_date
	 */
	get_classic_literature_publication_date(){
		this.$get("~/api/classic_literature/get_list?like=0", { page: 1, size: 10, "publication_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_classic_literature_publication_date = json.result.list;
			result_classic_literature_publication_date.map(o => o.title = o['publication_date'])
	  			this.result_classic_literature_publication_date = result_classic_literature_publication_date
		 	}
		});
	},
	/**
	 * 获取video_name
	 */
	get_video_data_video_name(){
		this.$get("~/api/video_data/get_list?like=0", { page: 1, size: 10, "video_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_video_data_video_name = json.result.list;
			result_video_data_video_name.map(o => o.title = o['video_name'])
	  			this.result_video_data_video_name = result_video_data_video_name
		 	}
		});
	},
	/**
	 * 获取author
	 */
	get_video_data_author(){
		this.$get("~/api/video_data/get_list?like=0", { page: 1, size: 10, "author": this.query.word }, (json) => {
		  if (json.result) {
			var result_video_data_author = json.result.list;
			result_video_data_author.map(o => o.title = o['author'])
	  			this.result_video_data_author = result_video_data_author
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_registered_user_user_name();
		this.get_development_history_title();
		this.get_books_and_materials_book_name();
		this.get_books_and_materials_author();
		this.get_classic_literature_document_name();
		this.get_classic_literature_publication_date();
		this.get_video_data_video_name();
		this.get_video_data_author();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_registered_user_user_name();
	  this.get_development_history_title();
	  this.get_books_and_materials_book_name();
	  this.get_books_and_materials_author();
	  this.get_classic_literature_document_name();
	  this.get_classic_literature_publication_date();
	  this.get_video_data_video_name();
	  this.get_video_data_author();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
