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
            <log-table
            v-if="logs && logs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:logs="logs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-logs="getAllLogs"
             >

            </log-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LogTable from "@/components/LogTable";
import LogService from "../services/LogService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LogTable,
  },
  data() {
    return {
      logs: [],
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
    async getAllLogs(sortBy='logId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LogService.getAllLogs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.logs.length) {
					this.logs = response.data.logs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching logs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching log details:", error);
      }
    },
  },
  mounted() {
    this.getAllLogs();
  },
  created() {
    this.$root.$on('searchQueryForLogsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLogs();
    })
  }
};
</script>
<style></style>
