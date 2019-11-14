import Vue from 'vue'
import Vuetify from "vuetify"
import '@babel/polyfill'
import 'api/resource'
import router from "router/router"
import Vuelidate from "vuelidate"
import App from 'pages/App.vue'
import store from "store/store";
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuelidate)
Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    store,
    router,
    render: a => a(App)
})





/*
var cargoApi = Vue.resource('/dbCargo{/id}');
var weightApi = Vue.resource('/dbWeight{/id}');
var conditionApi = Vue.resource('/dbCondition{/id}');

Vue.component('add-cargo-form', {
    props: ['weights', 'conditions', 'cargos'],
    data: function(){
        return {
            amount: '',
            weight_id: '',
            length: '',
            width: '',
            height: '',
            condition_values: [],
            weight: '',
            weightObject: '',
            conditionsObjects: []
        }
    },
    template:
        '<div>' +
            '<input type="number" placeholder="Enter the amount" v-model="amount"/></br>' +
            '<input type="number" placeholder="Enter the weight" v-model="weight"/>' +
            '<select v-model="weight_id" v-on:change="change"><option v-for="weight in weights" v-bind:value="weight.id" >{{ weight.name }}</option></select></br>' +
            '<input type="number" placeholder="Enter the lenght" v-model="length"/></br>' +
            '<input type="number" placeholder="Enter the width" v-model="width"/></br>' +
            '<input type="number" placeholder="Enter the height" v-model="height"/></br>' +
            '<select multiple v-model="condition_values" v-on:change="changeCondition"><option v-for="condition in conditions" v-bind:value="condition.id">{{ condition.name }}</option></select></br>' +
            '<input type="button" value="Add cargo" @click="add"/>' +
        '</div>',
    methods: {
        add: function () {

            var cargo = {
                amount: Number(this.amount),
                weight: this.weightObject,
                weight_value: Number(this.weight),
                length: Number(this.length),
                width: Number(this.width),
                height: Number(this.height),
                special_conditions: this.conditionsObjects
            };

            cargoApi.save({}, cargo).then(result =>
                result.json().then(data =>
                    this.cargos.push(data)
                )
            );

            this.amount= '';
            this.weight_id= '';
            this.length= '';
            this.width= '';
            this.height= '';
            this.condition_values= [];
            this.weight= '';
            this.weightObject= '';
            this.conditionsObjects= [];
        },

        change: function () {
            weightApi.get({
                id: this.weight_id
            }).then(result =>
                result.json().then(data => {
                        this.weightObject = data;
                    }
                )
            );
        },

        changeCondition: function () {
            this.conditionsObjects = [];
            for (let i = 0; i < this.condition_values.length; i++ ){
                conditionApi.get({
                    id: this.condition_values[i]
                }).then(result =>
                    result.json().then(data => {
                            this.conditionsObjects.push(data);
                        }
                    )
                )
            }
        }
    }
});

Vue.component('special-condition-row', {
    props: ['special_condition'],
    template: '<li>{{ special_condition.name }}</li>'
});

Vue.component('special-condition-list', {
    props: ['special_conditions'],
    template: '<ul><special-condition-row v-for="special_condition in special_conditions" :key="special_condition.id" :special_condition="special_condition" /></ul>'
});

Vue.component('cargo-row', {
    props: ['cargo'],
    template: '<div><i>{{ cargo.id }}  </i>{{ cargo.amount }}  {{ cargo.weight.name }} {{ cargo.length }}<special-condition-list :special_conditions="cargo.special_conditions" /></div>'
});

Vue.component('cargo-list', {
    props: ['cargos', 'weights', 'conditions'],
    template:
        '<div>' +
            '<add-cargo-form :weights="weights" :conditions="conditions" :cargos="cargos"/>' +
            '<cargo-row v-for="cargo in cargos" :key="cargo.id" :cargo="cargo" />' +
        '</div>',
    created: function () {
        cargoApi.get().then(result =>
            result.json().then(data =>
                data.forEach(cargo => this.cargos.push(cargo))
            )
        ),
        weightApi.get().then(result =>
                result.json().then(data =>
                    data.forEach(weight => this.weights.push(weight))
                )
        ),
        conditionApi.get().then(result =>
                result.json().then(data =>
                    data.forEach(condition => this.conditions.push(condition))
                )
        )
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<cargo-list :cargos="cargos" :weights="weights" :conditions="conditions"/>',
    data: {
        cargos: [],
        weights: [],
        conditions: []
    }
});*/
