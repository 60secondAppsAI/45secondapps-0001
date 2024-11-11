<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <fix-table
            v-if="fixs && fixs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:fixs="fixs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-fixs="getAllFixs"
             >

            </fix-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FixTable from "@/components/FixTable";
import FixService from "../services/FixService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FixTable,
  },
  data() {
    return {
      fixs: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllFixs(sortBy='fixId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FixService.getAllFixs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.fixs.length) {
					this.fixs = response.data.fixs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching fixs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching fix details:", error);
      }
    },
  },
  mounted() {
    this.getAllFixs();
  },
  created() {
    this.$root.$on('searchQueryForFixsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFixs();
    })
  }
};
</script>
<style></style>
