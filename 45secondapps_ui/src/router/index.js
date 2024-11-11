import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import AppGenerators from  '@/pages/AppGenerators.vue';
import AppGeneratorDetail from  '@/pages/AppGeneratorDetail.vue';
import Requests from  '@/pages/Requests.vue';
import RequestDetail from  '@/pages/RequestDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Templates from  '@/pages/Templates.vue';
import TemplateDetail from  '@/pages/TemplateDetail.vue';
import Apps from  '@/pages/Apps.vue';
import AppDetail from  '@/pages/AppDetail.vue';
import Features from  '@/pages/Features.vue';
import FeatureDetail from  '@/pages/FeatureDetail.vue';
import AppFeatures from  '@/pages/AppFeatures.vue';
import AppFeatureDetail from  '@/pages/AppFeatureDetail.vue';
import Accounts from  '@/pages/Accounts.vue';
import AccountDetail from  '@/pages/AccountDetail.vue';
import Billings from  '@/pages/Billings.vue';
import BillingDetail from  '@/pages/BillingDetail.vue';
import Subscriptions from  '@/pages/Subscriptions.vue';
import SubscriptionDetail from  '@/pages/SubscriptionDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Categorys from  '@/pages/Categorys.vue';
import CategoryDetail from  '@/pages/CategoryDetail.vue';
import Components from  '@/pages/Components.vue';
import ComponentDetail from  '@/pages/ComponentDetail.vue';
import AppComponents from  '@/pages/AppComponents.vue';
import AppComponentDetail from  '@/pages/AppComponentDetail.vue';
import Logs from  '@/pages/Logs.vue';
import LogDetail from  '@/pages/LogDetail.vue';
import Developers from  '@/pages/Developers.vue';
import DeveloperDetail from  '@/pages/DeveloperDetail.vue';
import ApplicationDevelopers from  '@/pages/ApplicationDevelopers.vue';
import ApplicationDeveloperDetail from  '@/pages/ApplicationDeveloperDetail.vue';
import BugReports from  '@/pages/BugReports.vue';
import BugReportDetail from  '@/pages/BugReportDetail.vue';
import Fixs from  '@/pages/Fixs.vue';
import FixDetail from  '@/pages/FixDetail.vue';
import SupportTickets from  '@/pages/SupportTickets.vue';
import SupportTicketDetail from  '@/pages/SupportTicketDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/appGenerators',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/appGenerators',
		name: 'AppGenerators',
		layout: DefaultLayout,
		component: AppGenerators,
	},
	{
	    path: '/appGenerator/:appGeneratorId', 
	    name: 'AppGeneratorDetail',
		layout: DefaultLayout,
	    component: AppGeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/requests',
		name: 'Requests',
		layout: DefaultLayout,
		component: Requests,
	},
	{
	    path: '/request/:requestId', 
	    name: 'RequestDetail',
		layout: DefaultLayout,
	    component: RequestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/templates',
		name: 'Templates',
		layout: DefaultLayout,
		component: Templates,
	},
	{
	    path: '/template/:templateId', 
	    name: 'TemplateDetail',
		layout: DefaultLayout,
	    component: TemplateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/apps',
		name: 'Apps',
		layout: DefaultLayout,
		component: Apps,
	},
	{
	    path: '/app/:appId', 
	    name: 'AppDetail',
		layout: DefaultLayout,
	    component: AppDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/features',
		name: 'Features',
		layout: DefaultLayout,
		component: Features,
	},
	{
	    path: '/feature/:featureId', 
	    name: 'FeatureDetail',
		layout: DefaultLayout,
	    component: FeatureDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/appFeatures',
		name: 'AppFeatures',
		layout: DefaultLayout,
		component: AppFeatures,
	},
	{
	    path: '/appFeature/:appFeatureId', 
	    name: 'AppFeatureDetail',
		layout: DefaultLayout,
	    component: AppFeatureDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/accounts',
		name: 'Accounts',
		layout: DefaultLayout,
		component: Accounts,
	},
	{
	    path: '/account/:accountId', 
	    name: 'AccountDetail',
		layout: DefaultLayout,
	    component: AccountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/billings',
		name: 'Billings',
		layout: DefaultLayout,
		component: Billings,
	},
	{
	    path: '/billing/:billingId', 
	    name: 'BillingDetail',
		layout: DefaultLayout,
	    component: BillingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/subscriptions',
		name: 'Subscriptions',
		layout: DefaultLayout,
		component: Subscriptions,
	},
	{
	    path: '/subscription/:subscriptionId', 
	    name: 'SubscriptionDetail',
		layout: DefaultLayout,
	    component: SubscriptionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/categorys',
		name: 'Categorys',
		layout: DefaultLayout,
		component: Categorys,
	},
	{
	    path: '/category/:categoryId', 
	    name: 'CategoryDetail',
		layout: DefaultLayout,
	    component: CategoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/components',
		name: 'Components',
		layout: DefaultLayout,
		component: Components,
	},
	{
	    path: '/component/:componentId', 
	    name: 'ComponentDetail',
		layout: DefaultLayout,
	    component: ComponentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/appComponents',
		name: 'AppComponents',
		layout: DefaultLayout,
		component: AppComponents,
	},
	{
	    path: '/appComponent/:appComponentId', 
	    name: 'AppComponentDetail',
		layout: DefaultLayout,
	    component: AppComponentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/logs',
		name: 'Logs',
		layout: DefaultLayout,
		component: Logs,
	},
	{
	    path: '/log/:logId', 
	    name: 'LogDetail',
		layout: DefaultLayout,
	    component: LogDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/developers',
		name: 'Developers',
		layout: DefaultLayout,
		component: Developers,
	},
	{
	    path: '/developer/:developerId', 
	    name: 'DeveloperDetail',
		layout: DefaultLayout,
	    component: DeveloperDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/applicationDevelopers',
		name: 'ApplicationDevelopers',
		layout: DefaultLayout,
		component: ApplicationDevelopers,
	},
	{
	    path: '/applicationDeveloper/:applicationDeveloperId', 
	    name: 'ApplicationDeveloperDetail',
		layout: DefaultLayout,
	    component: ApplicationDeveloperDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bugReports',
		name: 'BugReports',
		layout: DefaultLayout,
		component: BugReports,
	},
	{
	    path: '/bugReport/:bugReportId', 
	    name: 'BugReportDetail',
		layout: DefaultLayout,
	    component: BugReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/fixs',
		name: 'Fixs',
		layout: DefaultLayout,
		component: Fixs,
	},
	{
	    path: '/fix/:fixId', 
	    name: 'FixDetail',
		layout: DefaultLayout,
	    component: FixDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/supportTickets',
		name: 'SupportTickets',
		layout: DefaultLayout,
		component: SupportTickets,
	},
	{
	    path: '/supportTicket/:supportTicketId', 
	    name: 'SupportTicketDetail',
		layout: DefaultLayout,
	    component: SupportTicketDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
