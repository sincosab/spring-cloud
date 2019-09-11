
import * as API from './'

export default {

  menus: params=>{
    return API.GET('/api-admin/menu/getMenu',params)
  },
  editMenu: params=>{
    return API.PUT('/api-admin/menu/editMenu',params)
  },
  menuIdsByRoleId: params=>{
    return API.GET('/api-admin/menu/roleId',params)
  },
  add: params=>{
    return API.POST('/api-admin/menu/addMenu',params)
  },
  remove: params=>{
    return API.DELETE('/api-admin/menu/deleteMenu',params)
  }
}
