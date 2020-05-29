using EatenAPI.Common.BLL;
using EatenAPI.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.BLL
{
    using Common.Req;
    using Common.Rsp;
    using EatenAPI.DAL.ViewModels;
    using System.Linq;
    public class PostsSvc : GenericSvc<PostsRep, Posts>
    {
        #region Override
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }
        public override SingleRsp Update(Posts m)
        {
            var res = new SingleRsp();
            var m1 = m.PostId > 0 ? _rep.Read(m.PostId) : _rep.Read(m.PostName);
            if (m1 == null)
                res.SetError("EZ103", "No Data.");
            else
            {
                res = base.Update(m);
                res.Data = m;
            }
            return res;
        }
        #endregion
     
        #region Methods
        public SingleRsp CreatePost(PostReq post)
        {
            var res = new SingleRsp();
            var postNew = new Posts()
            {
                AccountId = post.AccountId,
                PostName = post.PostName,
                Content = post.Content,
                Address = post.Address
            };
            res = _rep.CreatePost(postNew);
            return res;
        }
        public SingleRsp UpdatePost(PostReq post)
        {
            var res = new SingleRsp();
            var postUpdate = new Posts()
            {
                PostId = post.PostId,
                AccountId = post.AccountId,
                PostName = post.PostName,
                Content = post.Content,
                Address = post.Address
            };
            res = _rep.UpdatePost(postUpdate);
            return res;
        }
        public SingleRsp DeletePost(int id)
        {
            var res = new SingleRsp();
            var acc = _rep.All.First(p => p.PostId == id);
            res = _rep.DeletePost(acc);
            return res;
        }
        public IEnumerable<PostInfoViewModel> GetAllPostInfo()
        {
            var res = _rep.GetAllPostInfo();
            return res;
        }
        public List<Posts> SearchPost(string kw)
        {
            var res = _rep.SearchPost(kw);
            return res;
        }
        public bool AddPost(PostReq post)
        {
            bool res = _rep.AddPost(post);
            return res;
        }
        #endregion
    }
}
