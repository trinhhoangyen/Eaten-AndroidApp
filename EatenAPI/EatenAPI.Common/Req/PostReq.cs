using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.Common.Req
{
    public class PostReq
    {
        public int PostId { get; set; }
        public int? AccountId { get; set; }
        public string PostName { get; set; }
        public string Content { get; set; }
        public string Address { get; set; }
        public string PictureURL { get; set; }
    }
}
