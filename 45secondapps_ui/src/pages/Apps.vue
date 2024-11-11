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
            <app-table
            v-if="apps && apps.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:apps="apps"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-apps="getAllApps"
             >

            </app-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AppTable from "@/components/AppTable";
import AppService from "../services/AppService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AppTable,
  },
  data() {
    return {
      apps: [],
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
    async getAllApps(sortBy='appId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AppService.getAllApps(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.apps.length) {
					this.apps = response.data.apps;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching apps:", error);
        }
        
      } catch (error) {
        console.error("Error fetching app details:", error);
      }
    },
  },
  mounted() {
    this.getAllApps();
  },
  created() {
    this.$root.$on('searchQueryForAppsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllApps();
    })
  }
};
</script>
<style></style>
