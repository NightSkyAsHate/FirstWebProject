import Vue from 'vue'

const reviews = Vue.resource('/dbReview/{id}')

export default {
    save: review => reviews.save({}, review),
    update: review => reviews.update({id: review.id}, review),
    delete: review => reviews.delete({id: review.id}, review),
}