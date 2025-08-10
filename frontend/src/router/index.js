import { createRouter, createWebHistory } from 'vue-router'
import ListFlushTemplate from '@/components/ListFlushTemplate.vue'
import CreateFlushTemplate from '@/components/CreateFlushTemplate.vue'
import UpdateFlushTemplate from "@/components/UpdateFlushTemplate.vue";
import ListFlushTask from "@/components/ListFlushTask.vue";
const routes = [
    {
        path: '/templates',
        name: 'ListFlushTemplate',
        component: ListFlushTemplate
    },
    {
        path: '/templates/create',
        name: 'CreateFlushTemplate',
        component: CreateFlushTemplate
    },
    {
        path: '/templates/update',
        name: 'UpdateFlushTemplate',
        component: UpdateFlushTemplate
    },
    {
        path: '/templates/task',
        name: 'ListFlushTask',
        component: ListFlushTask
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
