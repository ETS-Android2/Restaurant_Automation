using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Tokens
    {
        public int Id { get; set; }
        public Guid Token { get; set; }
        public DateTime CreatedDate { get; set; }
        public DateTime ExpireDate { get; set; }

        public virtual Users IdNavigation { get; set; }
    }
}
