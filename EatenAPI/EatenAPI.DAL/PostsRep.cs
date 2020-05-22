using EatenAPI.Common.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL
{
    using Common.Rsp;
    using EatenAPI.DAL.ViewModels;
    using System.Linq;
    using System.Net.WebSockets;

    public class PostsRep : GenericRep<EatenDatabaseContext, Posts>
    {
        #region --Override--
        public override Posts Read(int id)
        {
            var res = All.FirstOrDefault(p => p.PostId == id);
            return res;
        }
        #endregion
        public int Remove(int id)
        {
            var m = All.First(i => i.PostId == id);
            m = base.Delete(m);
            return m.PostId;
        }

        public SingleRsp CreatePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Add(post);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }

        public SingleRsp UpdatePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Update(post);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }

        public SingleRsp DeletePost(Posts post)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Posts.Remove(post);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }

        public IEnumerable<PostInfoViewModel> GetAllPostInfo()
        {
            var context = new EatenDatabaseContext();

            var post = context.Posts.ToList();
            var picture = context.Pictures.ToList();
            var comment = context.Comments.ToList();
            var account = context.Accounts.ToList();

            var quantity = from p in post
                           join c in comment on p.PostId equals c.PostId
                           where c.React == 1
                           group p by p.PostId into temp
                           select new
                           {
                               PostId = temp.Key,
                               Quantity = temp.Count(),
                           };

            //join q in quantity on p.PostId equals q.PostId
            var res = from p in post
                      join pt in picture on p.PostId equals pt.PostId 
                      join a in account on p.AccountId equals a.AccountId
                      join q in quantity on p.PostId equals q.PostId into temp
                      from subtemp in temp.DefaultIfEmpty()
                      select new PostInfoViewModel
                      {
                          PostId = p.PostId,
                          AccountId = p.AccountId,
                          PostName = p.PostName,
                          Content = p.Content,
                          Address = p.Address,
                          Picture = pt.PictureURL,
                          DisplayName = a.DisplayName,
                          ReactQuantity = subtemp == null?0:subtemp.Quantity
                      };
            return res;
        }

    }
}
