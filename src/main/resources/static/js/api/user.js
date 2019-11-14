import Vue from 'vue'

const users = Vue.resource('/dbUser/{id}')
const userska = Vue.resource('/dbUser/validation')

export default {
    save: user => users.save({}, user),
    update: user => users.update({id: user.id}, user),
    getById: id => users.get({id}),
    validation: user => userska.get({}, user),
    get: data => users.get()
}